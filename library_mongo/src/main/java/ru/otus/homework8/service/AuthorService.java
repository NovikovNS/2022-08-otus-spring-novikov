package ru.otus.homework8.service;

import ru.otus.homework8.domain.Author;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
}
