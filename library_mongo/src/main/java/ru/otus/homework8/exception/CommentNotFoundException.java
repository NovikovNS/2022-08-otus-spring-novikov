package ru.otus.homework8.exception;

public class CommentNotFoundException extends LibraryException{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
