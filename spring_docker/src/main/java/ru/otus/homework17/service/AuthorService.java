package ru.otus.homework17.service;

import ru.otus.homework17.domain.Author;
import ru.otus.homework17.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
