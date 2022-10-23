package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao.AuthorRepository;
import ru.otus.homework7.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author getAuthorById(long authorId) {
        return authorRepository.getReferenceById(authorId);
    }

    @Override
    @Transactional
    public Author getAuthorByName(String authorName) {
        return authorRepository.findByName(authorName).orElse(
                authorRepository.getReferenceById(authorRepository.save(
                        Author.builder()
                                .name(authorName)
                                .build()).getId())
        );
    }
}
