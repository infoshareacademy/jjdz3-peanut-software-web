package peanut.medicine.web.validation.validators;

import peanut.medicine.web.validation.constraints.Pesel;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    private ConstraintValidatorContext constraintContext;

    @Override
    public void initialize(Pesel constraintAnnotation) {}

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        if(!hasElevenDigits(object))
        {
            addCustomViolation("Pesel must be made of 11 digits", constraintContext);
            return false;
        }

        if(!hasGoodMonth(object))
        {
            addCustomViolation("Pesel has wrong month part", constraintContext);
            return false;
        }

        if(!hasGoodDayOfMonth(object))
        {
            addCustomViolation("Pesel has wrong day of month part", constraintContext);
            return false;
        }

        if(!hasGoodControlDigit(object))
        {
            addCustomViolation("Pesel has incorrect control(last) digit", constraintContext);
            return false;
        }

        return true;
    }

    private boolean hasElevenDigits(String object)
    {
        return object.matches("^[0-9]{11}$");
    }

    private boolean hasGoodDayOfMonth(String object)
    {
        int[] februaryCodes = {82,2,22,42,62};
        int month = Integer.parseInt(object.substring(2,4));
        int day = Integer.parseInt(object.substring(4,6));

        boolean isFebruary = Arrays.stream(februaryCodes).anyMatch(x -> x == month);

        return (isFebruary && 1 <= day && day <= 28)
                ||
                (!isFebruary && 1 <= day && day <= 31); // one of these two conditions makes day of month valid
    }

    private boolean hasGoodMonth(String object)
    {
        int month = Integer.parseInt(object.substring(2,4));

        //check month (based on https://pl.wikipedia.org/wiki/PESEL#Data_urodzenia)
        int[] possibleMonthValues = {
                81,1,21,41,61,
                82,2,22,42,62,
                83,3,23,43,63,
                84,4,24,44,64,
                85,5,25,45,65,
                86,6,26,46,66,
                87,7,27,47,67,
                88,8,28,48,68,
                89,9,29,49,69,
                90,10,30,50,70,
                91,11,31,51,71,
                92,12,32,52,72
        };

        return Arrays.stream(possibleMonthValues).anyMatch(x -> x == month);
    }

    private boolean hasGoodControlDigit(String object)
    {
        //check control digit (based on https://pl.wikipedia.org/wiki/PESEL#Cyfra_kontrolna_i_sprawdzanie_poprawno.C5.9Bci_numeru)
        int dA = Integer.parseInt(object.substring(0,1));
        int dB = Integer.parseInt(object.substring(1,2));
        int dC = Integer.parseInt(object.substring(2,3));
        int dD = Integer.parseInt(object.substring(3,4));
        int dE = Integer.parseInt(object.substring(4,5));
        int dF = Integer.parseInt(object.substring(5,6));
        int dG = Integer.parseInt(object.substring(6,7));
        int dH = Integer.parseInt(object.substring(7,8));
        int dI = Integer.parseInt(object.substring(8,9));
        int dJ = Integer.parseInt(object.substring(9,10));
        int controlDigit = Integer.parseInt(object.substring(10,11));

        int controlSum = 9*dA + 7*dB + 3*dC + dD + 9*dE + 7*dF + 3*dG + dH + 9*dI + 7*dJ;
        return (controlSum % 10) == controlDigit;
    }

    private void addCustomViolation(String message, ConstraintValidatorContext constraintContext)
    {
        constraintContext.disableDefaultConstraintViolation();
        constraintContext.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
