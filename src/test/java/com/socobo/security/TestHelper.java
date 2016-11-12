package com.socobo.security;

import com.socobo.security.model.Status;
import com.socobo.security.model.User;

/**
 * Created by patrick on 12.11.16.
 */
public class TestHelper {

    static User getTestUser() {
        return new User("Joda","joda@jedimail.com","lightsaber", Status.AKTIVE);
    }


}
