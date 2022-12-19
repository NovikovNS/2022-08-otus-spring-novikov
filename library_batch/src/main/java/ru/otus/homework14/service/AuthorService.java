package ru.otus.homework14.service;

import ru.otus.homework14.domain.Author;

public interface AuthorService {

    Author getAuthorById(String authorId);
    Author getAuthorByName(String authorName);
}
