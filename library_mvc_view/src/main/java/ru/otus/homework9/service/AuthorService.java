package ru.otus.homework9.service;

import ru.otus.homework9.domain.Author;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
}
