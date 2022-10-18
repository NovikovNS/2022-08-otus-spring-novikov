package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.AuthorRepository;
import ru.otus.homework6.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author getAuthorById(int authorId) {
        return authorRepository.getAuthorById(authorId);
    }

    @Override
    @Transactional
    public Author getAuthorByName(String authorName) {
        return authorRepository.getAuthorByName(authorName).orElse(
                authorRepository.getAuthorById(authorRepository.saveNewAuthor(
                        Author.builder()
                                .name(authorName)
                                .build()))
        );
    }
}
