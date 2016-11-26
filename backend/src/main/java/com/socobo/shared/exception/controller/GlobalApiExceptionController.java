package com.socobo.shared.exception.controller;

import com.socobo.shared.exception.model.ApiException;
import com.socobo.shared.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.socobo.shared.message.MessageKey.INTERNAL_ERROR;
import static com.socobo.shared.message.MessageKey.VALIDATION_FAILED;

/**
 * Created by patrick on 14.11.16.
 */
@ControllerAdvice
public class GlobalApiExceptionController {

    private final static String LANG_HEADER = "Accept-Language";

    private MessageSource messageSource;

    @Autowired
    public GlobalApiExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = ApiException.class)
    public void handleApiException(
            @RequestHeader(LANG_HEADER) Locale locale,
            ApiException e,
            HttpServletResponse response) throws IOException{

        Message message = e.message();
        response.sendError(message.statusCodeValue(), message.localizedMessage(messageSource, locale));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handleValidationExceptions(
            @RequestHeader(LANG_HEADER) Locale locale,
            MethodArgumentNotValidException e,
            HttpServletResponse response) throws IOException{

            String className = extractSimpleClassName(e);
            Message message = new Message(VALIDATION_FAILED, className);

            response.sendError(message.statusCodeValue(), message.localizedMessage(messageSource, locale));
    }

    private String extractSimpleClassName(MethodArgumentNotValidException e) {
        return e.getBindingResult().getTarget().getClass().getSimpleName();
    }

    @ExceptionHandler(value = Exception.class)
    public void handleGenericExceptions(
            @RequestHeader(LANG_HEADER) Locale locale,
            HttpServletResponse response) throws IOException{

        Message message = new Message(INTERNAL_ERROR);

        response.sendError(message.statusCodeValue(), message.localizedMessage(messageSource, locale));
    }
}
