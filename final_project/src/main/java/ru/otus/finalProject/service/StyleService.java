package ru.otus.finalProject.service;

import ru.otus.finalProject.domain.Style;
import ru.otus.finalProject.rest.dto.StyleDto;

import java.util.List;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    List<StyleDto> getAllStyles();
}
