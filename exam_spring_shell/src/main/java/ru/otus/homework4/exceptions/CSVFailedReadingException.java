package ru.otus.homework4.exceptions;

public class CSVFailedReadingException extends BaseCSVException {
    public CSVFailedReadingException(String message) {
        super(message);
    }
}
