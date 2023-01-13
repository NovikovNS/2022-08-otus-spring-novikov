package ru.otus.homework16.service;

import ru.otus.homework16.domain.Author;
import ru.otus.homework16.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
