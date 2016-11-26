package com.socobo.security.validation.validationAnnotation;

/**
 * Created by patrick on 06.11.16.
 */
public class EmailPattern {
    //RegEx Source: http://stackoverflow.com/questions/4459474/hibernate-validator-email-accepts-askstackoverflow-as-valid
    private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
    private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

    static final String PATTERN =
            "^" + ATOM  + "+(\\." + ATOM + "+)*@"+ DOMAIN + "|" + IP_DOMAIN + ")$";
}
