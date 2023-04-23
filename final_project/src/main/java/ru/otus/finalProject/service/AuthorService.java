package ru.otus.finalProject.service;

import ru.otus.finalProject.domain.Author;
import ru.otus.finalProject.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long authorId);
    Author getAuthorByName(String authorName);
    List<AuthorDto> getAllAuthors();
}
