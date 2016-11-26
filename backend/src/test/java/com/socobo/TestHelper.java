package com.socobo;

import com.socobo.security.model.Status;
import com.socobo.security.model.User;

/**
 * Created by patrick on 12.11.16.
 */
public class TestHelper {

    public static User getTestUser() {
        return new User("Joda","joda@jedimail.com","lightsaber", Status.ACTIVE);
    }


}
