package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author getAuthorById(long authorId);
    Optional<Author> getAuthorByName(String authorName);
    long saveNewAuthor(Author authorName);
}
