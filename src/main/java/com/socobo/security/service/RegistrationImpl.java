package com.socobo.security.service;

import com.socobo.security.exception.RegistrationException;
import com.socobo.security.model.Role;
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
        failIfUserExists(user);
        setUserToInitialState(user);
        String encryptedPassword = encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    private void setUserToInitialState(User user) {
        user.deactivate();
        user.addRole(Role.USER);
    }

    public User getUser(String id){
        return userRepository.findById(id);
    }

    private void failIfUserExists(User user) throws RegistrationException{
        Optional<User> foundUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        foundUser.ifPresent((u) -> {
            throw new RegistrationException("User for username: "
                    + user.getUsername()
                    + " or email: "
                    + user.getEmail()
                    + " already exists");
        });
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
