package ru.otus.homework10.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.rest.dto.StyleDto;
import ru.otus.homework10.service.StyleService;

import java.util.List;

@RestController
@AllArgsConstructor
public class StyleController {
    private final StyleService styleService;

    @GetMapping("/api/styles")
    ResponseEntity<List<StyleDto>> getAllStyles() {
        return ResponseEntity.ok().body(styleService.getAllStyles());
    }
}
