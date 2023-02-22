package ru.otus.homework18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework18.dao.StyleRepository;
import ru.otus.homework18.domain.Style;
import ru.otus.homework18.rest.dto.StyleDto;
import ru.otus.homework18.rest.dto.converter.StyleDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;
    private final StyleDtoConverter styleDtoConverter;

    @Override
    public Style getStyleById(long styleId) {
        return styleRepository.findById(styleId).get();
    }

    @Override
    public Style getStyleByName(String styleName) {
        return styleRepository.findByName(styleName).orElseGet(() ->
                styleRepository.findById(styleRepository.save(
                        Style.builder().name(styleName).build()).getId()).get());
    }

    @HystrixCommand(groupKey = "styleService", fallbackMethod = "fallbackGetAllStyles", commandKey = "getAllStyles")
    @Override
    public List<StyleDto> getAllStyles() {
        return styleRepository.findAll().stream().map(styleDtoConverter::mapToDto).collect(Collectors.toList());
    }

    private List<StyleDto> fallbackGetAllStyles() {
        log.debug("Method getAllStyles is working incorrectly. FallbackMethod 'fallbackGetAllAuthors' has launched");
        return List.of();
    }
}

