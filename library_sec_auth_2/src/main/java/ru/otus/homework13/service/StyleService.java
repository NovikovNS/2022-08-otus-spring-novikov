package ru.otus.homework13.service;

import ru.otus.homework13.domain.Style;
import ru.otus.homework13.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
