package com.socobo.security;

import com.socobo.TestHelper;
import com.socobo.security.model.Role;
import com.socobo.security.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by patrick on 17.11.16.
 */
public class RoleTest {

    @Test
    public void assignedTo_roleAddedToUser_returnsTrue(){

        Role role = Role.USER;
        User user = TestHelper.getTestUser();
        user.addRole(role);

        boolean isAssigned = role.assignedTo(user);

        assertTrue("Role must be assigned to the user", isAssigned);
    }

    @Test
    public void assignedTo_roleNotAddedToUser_returnsFalse(){

        Role role = Role.USER;
        User user = TestHelper.getTestUser();

        boolean isAssigned = role.assignedTo(user);

        assertFalse("Role must not be assigned to the user", isAssigned);
    }

}
