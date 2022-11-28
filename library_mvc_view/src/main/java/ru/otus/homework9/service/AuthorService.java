package ru.otus.homework9.service;

import ru.otus.homework9.domain.Author;
import ru.otus.homework9.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
