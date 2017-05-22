package peanut.medicine.web.validation.validators;

import peanut.medicine.web.validation.constraints.Pesel;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public void initialize(Pesel constraintAnnotation) {}

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        //check length
        if (!object.matches("^[0-9]{11}$"))
        {
            addCustomViolation("Pesel musi mieć 11 znaków", constraintContext);
            return false;
        }

        //check date part
        int month = Integer.parseInt(object.substring(2,4));
        int day = Integer.parseInt(object.substring(4,6));
        int[] februaryCodes = {82,2,22,42,62};

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

        boolean isGoodMonth = Arrays.stream(possibleMonthValues).anyMatch(x -> x == month);
        if(!isGoodMonth)
        {
            addCustomViolation("Pesel ma niepoprawną cześć odpowiadjącą za miesiąc", constraintContext);
            return false;
        }

        //check day of month
        boolean isFebruary = Arrays.stream(februaryCodes).anyMatch(x -> x == month);
        if (!(
                (isFebruary && 1 <= day && day <= 28) || (!isFebruary && 1 <= day && day <= 31)) // one of this makes day of month valid
                )
        {
            addCustomViolation("Pesel ma niepoprawną cześć odpowiadjącą za dzień miesiąca", constraintContext);
            return false;
        }

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
        if((controlSum % 10) != controlDigit)
        {
            addCustomViolation("Pesel ma niepoprawną cyfrę kontrolną", constraintContext);
            return false;
        }

        return true;
    }

    private void addCustomViolation(String message, ConstraintValidatorContext constraintContext)
    {
        constraintContext.disableDefaultConstraintViolation();
        constraintContext.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
