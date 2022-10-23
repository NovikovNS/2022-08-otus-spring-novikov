package ru.otus.homework7.service;

import ru.otus.homework7.domain.Style;

public interface StyleService {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
}
