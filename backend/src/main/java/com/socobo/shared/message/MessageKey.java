package com.socobo.shared.message;

import org.springframework.http.HttpStatus;

public enum MessageKey {

    INTERNAL_ERROR("global.internal.error", HttpStatus.INTERNAL_SERVER_ERROR),

    VALIDATION_FAILED("global.validation.failed", HttpStatus.BAD_REQUEST),

    REGISTER_USER_EXISTS("register.user.exists", HttpStatus.CONFLICT),
    REGISTER_USERNAME_REQ("register.username.required", HttpStatus.BAD_REQUEST),
    REGISTER_PW_MISMATCH("register.password.mismatch", HttpStatus.CONFLICT),
    REGISTER_PW_REQ("register.password.required", HttpStatus.BAD_REQUEST),
    REGISTER_PW_SIZE("register.password.length", HttpStatus.BAD_REQUEST),
    REGISTER_PW_CONFIRM_REQ("register.password.confirmation", HttpStatus.BAD_REQUEST),
    REGISTER_EMAIL_FORMAT("register.email.format", HttpStatus.BAD_REQUEST),
    REIGSTER_EMAIL_REQ("register.email.required", HttpStatus.BAD_REQUEST);


    private String key;
    private HttpStatus status;

    MessageKey(String key, HttpStatus status) {
        this.key = key;
        this.status = status;
    }

    public String key() {
        return key;
    }

    public HttpStatus statusCode() {
        return status;
    }

    public int statusCodeValue(){
        return status.value();
    }

    public String statusPhrase(){
        return this.statusCode().getReasonPhrase();
    }

    public String asValidationKey(){
        return "{" + key + "}";
    }
}