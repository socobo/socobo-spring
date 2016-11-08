package com.socobo.security;

import com.socobo.security.model.User;
import org.mockito.ArgumentMatcher;

/**
 * Created by patrick on 07.11.16.
 */
public class UserArgumentMatcher extends ArgumentMatcher<User>{

    private User expectedUser;

    public UserArgumentMatcher(User expectedUser) {
        this.expectedUser = expectedUser;
    }

    @Override
    public boolean matches(Object actualUser) {
        if(actualUser == expectedUser)
            return true;
        if(!(actualUser instanceof User))
            return false;
        User user = (User)actualUser;

        if(
            expectedUser.getUsername().equals(user.getUsername()) &&
            expectedUser.getPassword().equals(user.getPassword()) &&
            expectedUser.getEmail().equals(user.getEmail()) &&
            expectedUser.getStatus().equals(user.getStatus()))
            return true;
        return false;

    }
}
