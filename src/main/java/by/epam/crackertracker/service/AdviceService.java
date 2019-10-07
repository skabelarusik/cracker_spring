/**
 * service for working with advice dao
 * @author Andrey Krupin,  june-august 2019
 */


package by.epam.crackertracker.service;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.AdviceLengthValidator;
import by.epam.crackertracker.validator.IdValidator;
import by.epam.crackertracker.dao.AdviceDaoJdbcImpl;
import by.epam.crackertracker.exception.TrackerDBException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdviceService {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    private AdviceDaoJdbcImpl dao;

    public String selectRandomAdvice() throws TrackerServiceException {
        AdviceDaoJdbcImpl dao = new AdviceDaoJdbcImpl();
        String advice = null;
        try {
           dao.selectRandomAdvice();
        } catch (TrackerDBException e) {
            LOGGER.error("wrong service select random advice", e);
            throw new TrackerServiceException("Wrong service select random advice ", e);
        }
        return advice;
    }

    public List<Advice> selectAllAdvices() {
        return dao.selectAll();
    }

    public boolean addAdvice(String message) throws TrackerServiceException {
        boolean status = false;
        AdviceDaoJdbcImpl dao;
        AdviceLengthValidator validator = new AdviceLengthValidator();
        if(validator.isValidate(message)){
            dao = new AdviceDaoJdbcImpl();
            try {
                dao.insert(message);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service select random advice", e);
                throw new TrackerServiceException("Wrong service select random advice ", e);
            }
        }
       return status;
    }

    public boolean deleteAdviceById(String id) throws TrackerServiceException {
        boolean status = false;
        AdviceDaoJdbcImpl dao = null;
        IdValidator validator = new IdValidator();
        if(validator.isValidate(id)){
            dao = new AdviceDaoJdbcImpl();
            int numb = Integer.parseInt(id);
            try {
                dao.deleteById(numb);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete by id", e);
                throw new TrackerServiceException("Wrong service delete by id" , e);
            }
        }
        return status;
    }
}
