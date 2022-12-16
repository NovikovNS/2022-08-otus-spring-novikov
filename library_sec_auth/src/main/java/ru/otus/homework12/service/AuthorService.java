package ru.otus.homework12.service;

import ru.otus.homework12.domain.Author;
import ru.otus.homework12.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
