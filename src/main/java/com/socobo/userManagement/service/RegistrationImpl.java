package com.socobo.userManagement.service;

import com.socobo.userManagement.model.User;
import com.socobo.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return userRepository.save(getEncryptedPasswordUser(user));
    }

    private User getEncryptedPasswordUser(User user) {
        return new User(
                    user.getUsername(),
                    user.getEmail(),
                    passwordEncoder.encode(user.getPassword()));
    }
}
