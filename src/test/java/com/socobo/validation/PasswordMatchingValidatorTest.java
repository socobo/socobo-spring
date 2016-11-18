package com.socobo.validation;

import com.socobo.TestHelper;
import com.socobo.security.model.User;
import com.socobo.security.validation.validationAnnotation.PasswordMatchingValidator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by patrick on 17.11.16.
 */
public class PasswordMatchingValidatorTest {

    @Test
    public void isValid_twoMatchingPasswords_true(){

        User user = TestHelper.getTestUser();
        user.setRepeatedPassword(user.getPassword());

        PasswordMatchingValidator validator = new PasswordMatchingValidator();

        boolean matches = validator.isValid(user, null);

        assertTrue("Passwords must match", matches);
    }

    @Test
    public void isValid_twoNonMatchingPasswords_false(){

        User user = TestHelper.getTestUser();
        user.setRepeatedPassword("otherPassword");

        PasswordMatchingValidator validator = new PasswordMatchingValidator();

        boolean matches = validator.isValid(user, null);

        assertFalse("Passwords must match", matches);
    }
}
