package ru.otus.homework4.exceptions;

public class CSVFailedReadingException extends RuntimeException {
    public CSVFailedReadingException(String message) {
        super(message);
    }
}
