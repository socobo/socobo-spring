package com.socobo.security.validation.validationAnnotation;

import com.socobo.security.model.User;
import jdk.nashorn.internal.runtime.regexp.joni.MatcherFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by patrick on 06.11.16.
 */
public class EmailValidator implements ConstraintValidator<Email, String>{

    @Override
    public void initialize(Email constraintAnnotation) { }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        String cleanedEmail = email.trim();
        Pattern emailPattern = Pattern.compile(EmailPattern.PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(cleanedEmail);
        return emailMatcher.matches();
    }
}
