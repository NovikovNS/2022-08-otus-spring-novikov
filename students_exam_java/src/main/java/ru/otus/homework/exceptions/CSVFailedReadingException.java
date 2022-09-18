package ru.otus.homework.exceptions;

public class CSVFailedReadingException extends RuntimeException {
    public CSVFailedReadingException(String message) {
        super(message);
    }
}
