package com.socobo.security.service;

import com.socobo.security.exception.RegistrationException;
import com.socobo.security.model.Status;
import com.socobo.security.model.User;
import com.socobo.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by patrick on 05.11.16.
 */
@Service
public class RegistrationImpl implements Registration{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        verifyUserDoesntExist(user);
        setInaktiv(user);
        System.out.println("INAKTIVE?: " + user);
        return userRepository.save(getEncryptedPasswordUser(user));
    }

    private void setInaktiv(User user) {
        user.setStatus(Status.INAKTIV);
    }

    private void verifyUserDoesntExist(User user) {
        Optional<User> foundUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        foundUser.ifPresent((u) -> {
            throw new RegistrationException("User for username: "
                    + user.getUsername()
                    + " or email: "
                    + user.getEmail()
                    + " already exists");
        });
    }

    private User getEncryptedPasswordUser(User user) {
        return new User(
                    user.getUsername(),
                    user.getEmail(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getStatus());
    }
}
