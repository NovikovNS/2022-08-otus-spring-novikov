package ru.otus.homework7.dao;

import ru.otus.homework7.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author getAuthorById(int authorId);
    Optional<Author> getAuthorByName(String authorName);
    int saveNewAuthor(Author authorName);
}
