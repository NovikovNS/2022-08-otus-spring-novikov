package ru.otus.homework.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework.config.AppConfig;

@Component
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final AppConfig appConfig;

    public MessageServiceImpl(MessageSource messageSource, AppConfig appConfig) {
        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }

    @Override
    public String getMessage(String message) {
        return messageSource.getMessage(message,  null, appConfig.getLocale());
    }
}
