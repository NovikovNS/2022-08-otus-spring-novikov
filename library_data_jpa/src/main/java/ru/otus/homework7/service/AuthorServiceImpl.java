package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import ru.otus.homework7.dao.AuthorRepository;
import ru.otus.homework7.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(long authorId) {
        return authorRepository.getReferenceById(authorId);
    }

    @Override
    public Author getAuthorByName(String authorName) {
        return authorRepository.findByName(authorName).orElse(
                authorRepository.getReferenceById(authorRepository.save(
                        Author.builder()
                                .name(authorName)
                                .build()).getId())
        );
    }
}
