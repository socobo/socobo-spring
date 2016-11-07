package com.socobo.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by patrick on 06.11.16.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistrationException extends RuntimeException{

    public RegistrationException(String message) {
        super(message);
    }

}
