package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author getAuthorById(int authorId);
    Optional<Author> getAuthorByName(String authorName);
    Author saveNewAuthor(Author authorName);
}
