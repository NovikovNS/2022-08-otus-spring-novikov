package ru.otus.homework5.service;

import ru.otus.homework5.domain.Author;

public interface AuthorService {

    Author getAuthorById(int authorId);
    Author getAuthorByName(String authorName);
}
