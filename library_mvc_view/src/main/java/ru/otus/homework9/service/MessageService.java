package ru.otus.homework9.service;

public interface MessageService {
    String getMessage(String message);
    String getMessageWithArgs(String message, String[] args);
}
