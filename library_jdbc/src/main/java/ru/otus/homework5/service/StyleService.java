package ru.otus.homework5.service;

import ru.otus.homework5.domain.Style;

public interface StyleService {
    Style getStyleById(int styleId);
    Style getStyleByName(String styleName);
}
