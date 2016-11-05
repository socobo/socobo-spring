package com.socobo.userManagement.service;

import com.socobo.userManagement.model.User;
import com.socobo.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 * Created by patrick on 05.11.16.
 */
public class RegistrationImpl implements Registration{

    private UserRepository userRepository;

    @Autowired
    public RegistrationImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}
