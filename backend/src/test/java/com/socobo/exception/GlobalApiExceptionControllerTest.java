package com.socobo.exception;

import com.socobo.TestHelper;
import com.socobo.shared.exception.controller.GlobalApiExceptionController;
import com.socobo.shared.exception.model.ApiException;
import com.socobo.shared.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.socobo.shared.message.MessageKey.*;
import static org.mockito.Mockito.*;

/**
 * Created by patrick on 07.11.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalApiExceptionControllerTest {

    @Mock
    private MessageSource messageSource;
    @Mock
    private MethodArgumentNotValidException validationException;
    @Mock
    private ApiException apiException;
    @Mock
    HttpServletResponse response;
    @Mock
    BindingResult bindingResult;

    @Test
    public void handleValidationExceptions_always_returnsValidationMessageAndBadRequestStatus() throws IOException {

        GlobalApiExceptionController controller = new GlobalApiExceptionController(messageSource);
        Locale usLocale = locale();
        Object[] replacements = replacements();
        when(validationException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getTarget()).thenReturn(TestHelper.getTestUser());
        when(messageSource.getMessage(VALIDATION_FAILED.key(), replacements, usLocale))
                .thenReturn("Test Message");

        controller.handleValidationExceptions(usLocale, validationException, response);

        verify(response).sendError(VALIDATION_FAILED.statusCodeValue(), "Test Message");

    }

    @Test
    public void handleApiException_always_returnsValidationMessageAndStatus() throws IOException {

        GlobalApiExceptionController controller = new GlobalApiExceptionController(messageSource);
        Locale usLocale = locale();
        Object[] replacements = {};
        when(apiException.message()).thenReturn(new Message(REGISTER_USER_EXISTS));
        when(messageSource.getMessage(REGISTER_USER_EXISTS.key(), replacements, usLocale))
                .thenReturn("Test Message");

        controller.handleApiException(usLocale, apiException, response);

        verify(response).sendError(REGISTER_USER_EXISTS.statusCodeValue(), "Test Message");
    }

    @Test
    public void handleAnyException_always_returnsInternalServerError() throws IOException {

        GlobalApiExceptionController controller = new GlobalApiExceptionController(messageSource);
        Locale usLocale = locale();
        Object[] replacements = {};
        when(messageSource.getMessage(INTERNAL_ERROR.key(), replacements, usLocale))
                .thenReturn("Test Message");

        controller.handleGenericExceptions(usLocale, response);

        verify(response).sendError(INTERNAL_ERROR.statusCodeValue(), "Test Message");
    }

    private Object[] replacements() {
        return new Object[]{"User"};
    }

    private Locale locale() {
        return new Locale("en", "US");
    }
}
