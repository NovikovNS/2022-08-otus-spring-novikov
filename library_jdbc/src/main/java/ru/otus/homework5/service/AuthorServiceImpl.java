package ru.otus.homework5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework5.dao.AuthorRepository;
import ru.otus.homework5.domain.Author;

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
        if (authorRepository.checkAuthorByName(authorName)) {
            return authorRepository.getAuthorByName(authorName);
        } else {
            long newAuthorId = authorRepository.saveNewAuthor(authorName);
            return authorRepository.getAuthorById(newAuthorId);
        }
    }
}
