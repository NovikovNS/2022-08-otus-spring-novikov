package ru.otus.homework13.exception;

public class BookNotFoundException extends LibraryException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
