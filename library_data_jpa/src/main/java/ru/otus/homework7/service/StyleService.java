package ru.otus.homework7.service;

import ru.otus.homework7.domain.Style;

public interface StyleService {
    Style getStyleById(int styleId);
    Style getStyleByName(String styleName);
}
