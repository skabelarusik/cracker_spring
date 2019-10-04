/**
 * jdbc dao class with different method for receive table reviews in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDaoJdbc implements ReviewDao {
    public static final String SELECT_ALL_REVIEW = "SELECT r.idreview, u.login, r.date, r.text from\n" +
            " review.ftl r INNER JOIN users u on r.name = u.id where r.show_review = ? order by r.date desc";
    public static final String DELETE_BY_ID = "UPDATE review.ftl set show_review = ? where idreview = ?";
    public static final String SELECT_BY_ID = "SELECT idreview from review.ftl where idreview = ?";
    public static final String INSERT_REVIEW = "INSERT INTO review.ftl (name, date, text) values (?,?,?)";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Review> selectAllReview(int show) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Review> list= new ArrayList<>();
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ALL_REVIEW);
            statement.setInt(1, show);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                LocalDate date = LocalDate.parse(resultSet.getString(3));
                String text = resultSet.getString(4);
                Review review = new Review(login, text, date);
                review.setId(id);
                list.add(review);
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select reviews");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }

    @Override
    public boolean deleteById(int id, int typeShow) throws  TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
           connection = ConnectionPool.getInstance().takeConnection();
            if(hasId(connection, id)) {
                statement = connection.prepareStatement(DELETE_BY_ID);
                statement.setInt(1, typeShow);
                statement.setInt(2, id);
                statement.executeUpdate();
                status = true;
                LOGGER.warn("Review id: " + id + " was delete");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete review.ftl");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public boolean insertReview(String sender, String text) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        boolean status = false;
        try{
        connection = ConnectionPool.getInstance().takeConnection();
        if(!dao.isUniqueUser(connection, sender)){
            int id = dao.selectIdByLogin(connection, sender);
            LocalDate date = LocalDate.now();
            statement = connection.prepareStatement(INSERT_REVIEW);
            statement.setInt(1, id);
            statement.setString(2, date.toString());
            statement.setString(3, text);
            statement.executeUpdate();
            status = true;
            LOGGER.warn("Review: " + text + " was insert");
        }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert review.ftl");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public boolean hasId(Connection connection, int id) throws TrackerDBException {
        boolean status;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            status  = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking id review.ftl");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }
}
