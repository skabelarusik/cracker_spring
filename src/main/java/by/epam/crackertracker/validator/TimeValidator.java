package by.epam.crackertracker.validator;

import by.epam.crackertracker.entity.MealTime;

public class TimeValidator implements ValidatorI{

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str != null && !str.isEmpty()){
            if(str.equalsIgnoreCase(MealTime.BREAKFAST.toString()) || str.equalsIgnoreCase(MealTime.SECOND_BREAKFAST.toString()) ||
                    str.equalsIgnoreCase(MealTime.DINNER.toString()) || str.equalsIgnoreCase(MealTime.SECOND_DINNER.toString()) ||
                    str.equalsIgnoreCase(MealTime.LUNCH.toString()) || str.equalsIgnoreCase(MealTime.SECOND_LUNCH.toString())) {
                status = true;
            }
        }
        return status;
    }
}
