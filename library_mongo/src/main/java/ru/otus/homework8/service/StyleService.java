package ru.otus.homework8.service;

import ru.otus.homework8.domain.Style;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
}
