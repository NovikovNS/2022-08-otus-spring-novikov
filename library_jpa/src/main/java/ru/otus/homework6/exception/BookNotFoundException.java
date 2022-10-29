package ru.otus.homework6.exception;

public class BookNotFoundException extends LibraryException{

    public BookNotFoundException(String message) {
        super(message);
    }
}
