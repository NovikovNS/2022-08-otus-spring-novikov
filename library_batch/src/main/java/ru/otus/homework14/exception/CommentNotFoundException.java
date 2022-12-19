package ru.otus.homework14.exception;

public class CommentNotFoundException extends LibraryException{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
