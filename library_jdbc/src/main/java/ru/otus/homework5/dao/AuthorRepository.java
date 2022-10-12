package ru.otus.homework5.dao;

import ru.otus.homework5.domain.Author;

public interface AuthorRepository {
    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    long saveNewAuthor(String authorName);
    boolean checkAuthorByName(String authorName);
}
