package com.socobo.userManagement.controller;

import com.socobo.userManagement.model.User;
import com.socobo.userManagement.repository.UserRepository;
import com.socobo.userManagement.service.Registration;
import org.apache.coyote.http11.filters.VoidInputFilter;
import static org.springframework.http.HttpStatus.*;

import static org.springframework.http.MediaType.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by patrick on 05.11.16.
 */
@RestController
@RequestMapping(
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
public class AuthController {

    private Registration registration;

    @Autowired
    public AuthController(Registration registration) {
        this.registration = registration;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody  User user) {
        User storedUser = registration.register(user);
        return new ResponseEntity<>(storedUser, OK);
    }

}
