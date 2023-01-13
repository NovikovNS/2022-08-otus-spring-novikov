package ru.otus.homework16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    @Autowired
    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String message) {
        return messageSource.getMessage(message,  null, Locale.ENGLISH);
    }

    @Override
    public String getMessageWithArgs(String message, String[] args) {
        return messageSource.getMessage(message,  args, Locale.ENGLISH);
    }
}
