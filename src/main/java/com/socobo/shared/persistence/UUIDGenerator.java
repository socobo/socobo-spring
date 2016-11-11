package com.socobo.shared.persistence;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by patrick on 09.11.16.
 */
public class UUIDGenerator{

    public static String createId() {
        return UUID.randomUUID().toString();
    }
}
