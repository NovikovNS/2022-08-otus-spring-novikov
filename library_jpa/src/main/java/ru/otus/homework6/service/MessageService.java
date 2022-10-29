package ru.otus.homework6.service;

public interface MessageService {
    String getMessage(String message);
    String getMessageWithArgs(String message, String[] args);
}
