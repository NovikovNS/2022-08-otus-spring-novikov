package ru.otus.finalProject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.dao.StyleRepository;
import ru.otus.finalProject.domain.Style;
import ru.otus.finalProject.rest.dto.StyleDto;
import ru.otus.finalProject.rest.dto.converter.StyleDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<StyleDto> getAllStyles() {
        return styleRepository.findAll().stream().map(styleDtoConverter::mapToDto).collect(Collectors.toList());
    }
}

