package ru.otus.homework12.service;

import ru.otus.homework12.domain.Style;
import ru.otus.homework12.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
