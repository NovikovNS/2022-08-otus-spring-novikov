package ru.otus.finalProject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.dao.AuthorRepository;
import ru.otus.finalProject.domain.Author;
import ru.otus.finalProject.rest.dto.AuthorDto;
import ru.otus.finalProject.rest.dto.converter.AuthorDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;

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
