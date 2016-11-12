package com.socobo.security;

import com.socobo.security.exception.RegistrationException;
import com.socobo.security.model.Role;
import com.socobo.security.model.Status;
import com.socobo.security.model.User;
import com.socobo.security.repository.UserRepository;
import com.socobo.security.service.RegistrationImpl;
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

        thrown.expect(RegistrationException.class);
        thrown.expectMessage(equalTo("User for username: "
                + userToRegister.getUsername()
                + " or email: "
                + userToRegister.getEmail()
                + " already exists"));

        registration.register(userToRegister);

    }

    private void userHasValidInitialState(User userToRegister) {
        assertThat(userToRegister.getPassword(), equalTo("XXXXX"));
        assertThat(userToRegister.getStatus(), equalTo(Status.INAKTIV));
        assertTrue(userToRegister.hasRole(Role.USER));
    }


    private RegistrationImpl getRegistration() {
        return new RegistrationImpl(userRepository, passwordEncoder);
    }

}
