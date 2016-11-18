package com.socobo.security;

import com.socobo.TestHelper;
import com.socobo.security.model.Role;
import com.socobo.security.model.Status;
import com.socobo.security.model.User;
import com.socobo.security.repository.UserRepository;
import com.socobo.security.service.RegistrationImpl;
import com.socobo.shared.exception.model.ApiException;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * Created by patrick on 07.11.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistraiontImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MessageSource messageSource;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void register_nonExistingUser_saveIsCalled(){

        RegistrationImpl registration = getRegistration();
        User userToRegister = TestHelper.getTestUser();
        when(userRepository.findByUsernameOrEmail(userToRegister.getUsername(), userToRegister.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userToRegister.getPassword())).thenReturn("XXXXX");

        registration.register(userToRegister);

        verify(userRepository).save(any(User.class));
        userHasValidInitialState(userToRegister);
    }

    @Test
    public void register_existingUser_registrationException(){

        RegistrationImpl registration = getRegistration();
        User userToRegister = TestHelper.getTestUser();
        when(userRepository.findByUsernameOrEmail(userToRegister.getUsername(), userToRegister.getEmail())).thenReturn(Optional.of(userToRegister));

        thrown.expect(ApiException.class);
        thrown.expectMessage(equalTo(HttpStatus.CONFLICT.getReasonPhrase()));
        registration.register(userToRegister);

    }

    private void userHasValidInitialState(User userToRegister) {
        assertThat(userToRegister.getPassword(), equalTo("XXXXX"));
        assertThat(userToRegister.getStatus(), equalTo(Status.INACTIVE));
        assertTrue(userToRegister.hasRole(Role.USER));
    }


    private RegistrationImpl getRegistration() {
        return new RegistrationImpl(userRepository, passwordEncoder);
    }

}
