package ru.otus.homework10.service;

import ru.otus.homework10.domain.Author;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
}
