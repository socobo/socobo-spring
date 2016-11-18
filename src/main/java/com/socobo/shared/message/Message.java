package com.socobo.shared.message;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.Locale;

public class Message {

    private MessageKey messageKey;
    private Object[] replacements = {};

    public Message(MessageKey messageKey) {
        this.messageKey = messageKey;
    }

    public Message(MessageKey messageKey,
                   Object... replacements) {
        this.messageKey = messageKey;
        this.replacements = replacements;
    }

    public String statusPhrase() {
        return messageKey.statusCode().getReasonPhrase();
    }

    public HttpStatus statusCode() {
        return messageKey.statusCode();
    }

    public int statusCodeValue() {
        return messageKey.statusCodeValue();
    }

    public String localizedMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(messageKey.key(), replacements, locale);
    }
}