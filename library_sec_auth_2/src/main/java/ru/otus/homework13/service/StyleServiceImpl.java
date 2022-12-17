package ru.otus.homework13.service;

import org.springframework.stereotype.Service;
import ru.otus.homework13.dao.StyleRepository;
import ru.otus.homework13.domain.Style;
import ru.otus.homework13.dto.StyleDto;
import ru.otus.homework13.dto.converter.StyleDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;
    private final StyleDtoConverter styleDtoConverter;

    public StyleServiceImpl(StyleRepository styleRepository, StyleDtoConverter styleDtoConverter) {
        this.styleRepository = styleRepository;
        this.styleDtoConverter = styleDtoConverter;
    }

    @Override
    public Style getStyleById(long styleId) {
        return styleRepository.findById(styleId).get();
    }

    @Override
    public Style getStyleByName(String styleName) {
        return styleRepository.findByName(styleName).orElseGet(() -> styleRepository.findById(styleRepository.save(
                Style.builder().name(styleName).build()).getId()).get());
        }

    @Override
    public List<StyleDto> getAllStyles() {
        return styleRepository.findAll().stream().map(styleDtoConverter::mapToDto).collect(Collectors.toList());
    }
}

