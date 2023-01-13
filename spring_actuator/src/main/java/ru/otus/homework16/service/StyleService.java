package ru.otus.homework16.service;

import ru.otus.homework16.domain.Style;
import ru.otus.homework16.rest.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
