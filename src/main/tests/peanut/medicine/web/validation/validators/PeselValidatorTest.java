package peanut.medicine.web.validation.validators;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintValidatorContext;

/**
 * Created by bartman3000 on 28.05.17.
 */
public class PeselValidatorTest {

    private static PeselValidator validator;
    private ConstraintValidatorContext constraintContext;

    @Before
    public void setUp() throws Exception {
        validator = new PeselValidator();
    }

    @Test
    public void isValid() {

    }

}