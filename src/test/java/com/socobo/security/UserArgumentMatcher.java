package com.socobo.security;

import com.socobo.security.model.Role;
import com.socobo.security.model.User;
import org.mockito.ArgumentMatcher;

import java.util.Objects;
import java.util.Set;

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
            expectedUser.getStatus().equals(user.getStatus())){
            Set<Role> texpectedRoles = expectedUser.getRoles();
            Set<Role> roles = user.getRoles();
            if(Objects.nonNull(texpectedRoles) && Objects.nonNull(roles)){
                return texpectedRoles.equals(roles);
            }else{
                return roles == texpectedRoles;
            }
        }
        return false;
    }
}
