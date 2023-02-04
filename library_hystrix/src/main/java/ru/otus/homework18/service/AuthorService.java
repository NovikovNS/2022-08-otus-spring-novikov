package ru.otus.homework18.service;

import ru.otus.homework18.domain.Author;
import ru.otus.homework18.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
