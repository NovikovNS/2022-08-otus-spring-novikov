package ru.otus.homework7.dao;

import ru.otus.homework7.domain.Style;

import java.util.Optional;

public interface StyleRepository {
    Style getStyleById(int styleId);
    Optional<Style> getStyleByName(String styleName);
    int saveNewStyle(Style style);
}
