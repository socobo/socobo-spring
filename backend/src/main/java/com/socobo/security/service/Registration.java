package com.socobo.security.service;

import com.socobo.security.model.User;

/**
 * Created by patrick on 05.11.16.
 */
public interface Registration {

    User register(User user);

    User getUser(String id);

}
