package ru.otus.homework13.service;

import org.springframework.stereotype.Service;
import ru.otus.homework13.dao.AuthorRepository;
import ru.otus.homework13.domain.Author;
import ru.otus.homework13.dto.AuthorDto;
import ru.otus.homework13.dto.converter.AuthorDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorDtoConverter authorDtoConverter) {
        this.authorRepository = authorRepository;
        this.authorDtoConverter = authorDtoConverter;
    }

    @Override
    public Author getAuthorById(long authorId) {
        return authorRepository.findById(authorId).get();
    }

    @Override
    public Author getAuthorByName(String authorName) {
        return authorRepository.findByName(authorName).orElseGet(() ->
                authorRepository.findById(authorRepository.save(
                        Author.builder()
                                .name(authorName)
                                .build()).getId()).get()
        );
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorDtoConverter::mapToDto).collect(Collectors.toList());
    }
}
