package ru.otus.homework17.service;

import ru.otus.homework17.domain.Style;
import ru.otus.homework17.rest.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
