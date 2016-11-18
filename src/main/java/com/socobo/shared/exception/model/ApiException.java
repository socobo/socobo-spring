package com.socobo.shared.exception.model;

import com.socobo.shared.message.Message;

/**
 * Created by patrick on 14.11.16.
 */
public class ApiException extends RuntimeException{

    private Message message;

    public ApiException(Message message){
        super(message.statusPhrase());
        this.message = message;
    }

    public Message message(){
        return this.message;
    }
}
