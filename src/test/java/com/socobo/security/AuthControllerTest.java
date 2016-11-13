package com.socobo.security;

import com.socobo.security.controller.AuthController;
import com.socobo.security.exception.RegistrationException;
import com.socobo.security.model.User;
import com.socobo.security.service.Registration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by patrick on 12.11.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    BindingResult bindingResult;
    @Mock
    User user;
    @Mock
    Registration registration;

    @Test
    public void register_noErrors_sendsNormalResponse(){

        when(registration.register(user)).thenReturn(user);
        when(bindingResult.hasErrors()).thenReturn(false);

        AuthController authController = new AuthController(registration);

        ResponseEntity<User> response = authController.register(user, bindingResult);

        assertThat(new ResponseEntity<User>(user, HttpStatus.CREATED), equalTo(response));
    }

    @Test
    public void register_hasErrors_throwsRegistrationException(){

        when(registration.register(user)).thenReturn(user);
        when(bindingResult.hasErrors()).thenReturn(true);

        AuthController authController = new AuthController(registration);

        thrown.expect(RegistrationException.class);

        authController.register(user, bindingResult);

    }

}
