package ru.otus.homework8.service;

import ru.otus.homework8.domain.Style;

public interface StyleService {
    Style getStyleById(String styleId);
    Style getStyleByName(String styleName);
}
