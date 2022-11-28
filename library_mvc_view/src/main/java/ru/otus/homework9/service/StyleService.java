package ru.otus.homework9.service;

import ru.otus.homework9.domain.Style;
import ru.otus.homework9.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
