package ru.otus.homework16.exception;

public class BookNotFoundException extends LibraryException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
