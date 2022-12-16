package ru.otus.homework11.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework11.dao.AuthorRepository;
import ru.otus.homework11.rest.dto.AuthorDto;
import ru.otus.homework11.rest.dto.converter.AuthorDtoConverter;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;

    @GetMapping("/api/authors")
    public Flux<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().map(authorDtoConverter::mapToDto);
    }
}
