package ru.otus.homework10.service;

import ru.otus.homework10.domain.Style;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
}
