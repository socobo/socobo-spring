package com.socobo.security.controller;

import com.socobo.security.exception.RegistrationException;
import com.socobo.security.model.User;
import com.socobo.security.service.Registration;

import static org.springframework.http.HttpStatus.*;

import static org.springframework.http.MediaType.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody @Valid User user, BindingResult result) {
        System.out.println(user);
        if(result.hasErrors())
            throw new RegistrationException(result.getAllErrors().toString());
        User storedUser = registration.register(user);
        return new ResponseEntity<>(storedUser, OK);
    }

}
