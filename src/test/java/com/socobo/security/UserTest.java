package com.socobo.security;

import com.socobo.security.model.Role;
import com.socobo.security.model.User;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by patrick on 12.11.16.
 */
public class UserTest {

    @Test
    public void addRole_always_addsARoleToTheUser(){
        User user = TestHelper.getTestUser();

        user.addRole(Role.USER);
        user.addRole(Role.ADMIN);

        assertTrue(user.hasRole(Role.ADMIN));
        assertTrue(user.hasRole(Role.USER));
    }

    @Test
    public void addRole_always_addsAUserToTheRole(){
        User user = TestHelper.getTestUser();

        user.addRole(Role.USER);

        assertTrue(Role.USER.assignedTo(user));
        assertFalse(Role.ADMIN.assignedTo(user));
    }

    @Test
    public void deleteRole_always_deletesARoleFromTheUser(){
        User user = TestHelper.getTestUser();
        user.addRole(Role.USER);

        user.deleteRole(Role.USER);

        assertFalse(user.hasRole(Role.USER));
    }

    @Test
    public void deleteRole_always_deletesAUserFromTheRole(){
        User user = TestHelper.getTestUser();
        user.addRole(Role.USER);

        user.deleteRole(Role.USER);

        assertFalse(Role.USER.assignedTo(user));
    }

    @Test
    public void deleteRole_noRoles_noEffect(){
        User user = TestHelper.getTestUser();

        user.deleteRole(Role.USER);

        assertFalse(Role.USER.assignedTo(user));
        assertFalse(user.hasRole(Role.USER));

    }

    @Test
    public void addRole_addDuplicateRole_userContainsOnlyUniqueRoles(){
        User user = TestHelper.getTestUser();

        user.addRole(Role.USER);
        user.addRole(Role.USER);

        assertThat(user.listRoles().size(), equalTo(1));
    }

}
