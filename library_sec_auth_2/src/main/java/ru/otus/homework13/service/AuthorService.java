package ru.otus.homework13.service;

import ru.otus.homework13.domain.Author;
import ru.otus.homework13.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
