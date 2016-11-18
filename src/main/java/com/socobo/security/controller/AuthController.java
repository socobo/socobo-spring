package com.socobo.security.controller;

import com.socobo.security.model.User;
import com.socobo.security.service.Registration;

import static org.springframework.http.HttpStatus.*;

import static org.springframework.http.MediaType.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping(path = "/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        User registeredUser = registration.register(user);
        return new ResponseEntity<>(registeredUser, CREATED);
    }

}
