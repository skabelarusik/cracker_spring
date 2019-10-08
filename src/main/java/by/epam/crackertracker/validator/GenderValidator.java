/**
 * it's a class for validate gender
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.entity.Gender;
import org.springframework.stereotype.Component;

@Component
public class GenderValidator implements ValidatorI{
    @Override
    public boolean isValidate(String str) {
        boolean status = false;

        if((str != null && !str.isEmpty()) && (str.equalsIgnoreCase(Gender.MALE.toString())
                || str.equalsIgnoreCase(Gender.FEMALE.toString()))){
                status = true;
            }
        return status;
    }
}
