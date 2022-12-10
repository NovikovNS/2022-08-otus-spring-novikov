package ru.otus.homework11.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework11.dao.StyleRepository;
import ru.otus.homework11.rest.dto.StyleDto;
import ru.otus.homework11.rest.dto.converter.StyleDtoConverter;

@RestController
@AllArgsConstructor
public class StyleController {
    private final StyleRepository styleRepository;
    private final StyleDtoConverter styleDtoConverter;

    @GetMapping("/api/styles")
    public Flux<StyleDto> getAllAuthors() {
        return styleRepository.findAll().map(styleDtoConverter::mapToDto);
    }
}
