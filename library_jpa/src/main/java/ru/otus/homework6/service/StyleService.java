package ru.otus.homework6.service;

import ru.otus.homework6.domain.Style;

public interface StyleService {
    Style getStyleById(int styleId);
    Style getStyleByName(String styleName);
}
