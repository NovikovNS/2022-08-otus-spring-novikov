package ru.otus.homework18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework18.dao.AuthorRepository;
import ru.otus.homework18.domain.Author;
import ru.otus.homework18.rest.dto.AuthorDto;
import ru.otus.homework18.rest.dto.converter.AuthorDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @HystrixCommand(groupKey = "authorService", fallbackMethod = "fallbackGetAllAuthors", commandKey = "getAllAuthors")
    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorDtoConverter::mapToDto).collect(Collectors.toList());
    }

    private List<AuthorDto> fallbackGetAllAuthors() {
        log.debug("Method getAllAuthors is working incorrectly. FallbackMethod 'fallbackGetAllAuthors' has launched");
        return List.of();
    }
}
