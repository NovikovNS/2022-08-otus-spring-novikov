package ru.otus.homework18.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework18.rest.dto.AuthorDto;
import ru.otus.homework18.service.AuthorService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok().body(authorService.getAllAuthors());
    }
}
