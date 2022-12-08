package ru.otus.homework10.service;

import ru.otus.homework10.domain.Author;
import ru.otus.homework10.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
