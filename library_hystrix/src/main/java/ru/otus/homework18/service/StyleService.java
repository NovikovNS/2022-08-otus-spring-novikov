package ru.otus.homework18.service;

import ru.otus.homework18.domain.Style;
import ru.otus.homework18.rest.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
