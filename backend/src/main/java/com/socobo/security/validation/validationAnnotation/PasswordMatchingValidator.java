package com.socobo.security.validation.validationAnnotation;

import com.socobo.security.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by patrick on 06.11.16.
 */
public class PasswordMatchingValidator implements ConstraintValidator<MatchingPasswords, User>{

    @Override
    public void initialize(MatchingPasswords constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        System.out.println("IN Validator: " + user);
        String password = user.getPassword();
        String repeatedPassword = user.getRepeatedPassword();
        return password.equals(repeatedPassword);
    }
}
