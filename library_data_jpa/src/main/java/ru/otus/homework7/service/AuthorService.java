package ru.otus.homework7.service;

import ru.otus.homework7.domain.Author;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
}
