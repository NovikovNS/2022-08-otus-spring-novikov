package ru.otus.homework12.service;

public interface MessageService {
    String getMessage(String message);
    String getMessageWithArgs(String message, String[] args);
}
