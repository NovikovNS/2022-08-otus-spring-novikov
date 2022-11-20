package ru.otus.homework9.service;

import ru.otus.homework9.domain.Style;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
}
