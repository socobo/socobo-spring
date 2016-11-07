package com.socobo.security;

import com.socobo.security.exception.RegistrationException;
import com.socobo.security.model.User;
import com.socobo.security.repository.UserRepository;
import com.socobo.security.service.Registration;
import com.socobo.security.service.RegistrationImpl;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
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
        User testUser = getTestUser();
        when(userRepository.findByUsernameOrEmail(testUser.getUsername(), testUser.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("XXXXX");

        User registeredUser = registration.register(testUser);

        verify(userRepository).findByUsernameOrEmail("Joda", "joda@jedimail.com");
        verify(passwordEncoder).encode("lightsaber");
        User jodaWithEncryptedPw = new User("Joda","joda@jedimail.com","XXXXX");
        verify(userRepository).save(argThat(new UserArgumentMatcher(jodaWithEncryptedPw)));
    }

    @Test
    public void register_existingUser_registrationException(){

        RegistrationImpl registration = getRegistration();
        User testUser = getTestUser();
        when(userRepository.findByUsernameOrEmail(testUser.getUsername(), testUser.getEmail())).thenReturn(Optional.of(testUser));

        thrown.expect(RegistrationException.class);
        thrown.expectMessage(equalTo("User for username: "
                + testUser.getUsername()
                + " or email: "
                + testUser.getEmail()
                + " already exists"));

        registration.register(testUser);

    }

    private User getTestUser() {
        return new User("Joda","joda@jedimail.com","lightsaber");
    }

    private RegistrationImpl getRegistration() {
        return new RegistrationImpl(userRepository, passwordEncoder);
    }

}
