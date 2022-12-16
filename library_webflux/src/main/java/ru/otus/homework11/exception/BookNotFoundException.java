package ru.otus.homework11.exception;

public class BookNotFoundException extends LibraryException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
