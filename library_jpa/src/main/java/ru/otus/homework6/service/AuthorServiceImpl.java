package ru.otus.homework6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework6.dao.AuthorRepository;
import ru.otus.homework6.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(int authorId) {
        return authorRepository.getAuthorById(authorId);
    }
    @Override
    public Author getAuthorByName(String authorName) {
        return authorRepository.getAuthorByName(authorName).orElse(
                authorRepository.getAuthorById(authorRepository.saveNewAuthor(
                        Author.builder()
                                .name(authorName)
                                .build()))
        );
    }
}
