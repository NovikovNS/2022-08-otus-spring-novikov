package ru.otus.homework10.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.rest.dto.AuthorDto;
import ru.otus.homework10.service.AuthorService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
