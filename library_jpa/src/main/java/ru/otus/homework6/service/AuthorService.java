package ru.otus.homework6.service;

import ru.otus.homework6.domain.Author;

public interface AuthorService {

    Author getAuthorById(int authorId);
    Author getAuthorByName(String authorName);
}
