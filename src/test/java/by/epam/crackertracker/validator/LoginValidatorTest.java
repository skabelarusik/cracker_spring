/**
 * it's a class for validate login user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginValidatorTest {
    LoginValidator validator = new LoginValidator();


    @Test
    public void testIsValidateNull(){
        Assert.assertFalse(validator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void testIsValidateEmpty(){
        Assert.assertFalse(validator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void testIsValidateOneSize(){
        Assert.assertFalse(validator.isValidate(ParametresTest.ONE_SIZE));
    }

    @Test
    public void testIsValidateBiggerSize(){
        Assert.assertFalse(validator.isValidate(ParametresTest.BIG_SIZE_400));
    }

    @Test
    public void testIsValidateCorrectSize(){
        Assert.assertTrue(validator.isValidate(ParametresTest.TEN_SIZE));
    }

}
