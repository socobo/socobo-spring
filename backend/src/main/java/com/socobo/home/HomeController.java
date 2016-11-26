package com.socobo.home;

import com.socobo.security.model.Status;
import com.socobo.security.model.User;
import com.socobo.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@EnableAutoConfiguration
public class HomeController {

    @Autowired
    private UserRepository repo;

    @RequestMapping("/")
    public String home() {

        repo.save(new User("testuser", "testemail", "password", Status.ACTIVE));
        return "Hello Socobo-Spring";
    }
}
