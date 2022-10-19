package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Style;

import java.util.Optional;

public interface StyleRepository {
    Style getStyleById(int styleId);
    Optional<Style> getStyleByName(String styleName);
    int saveNewStyle(Style style);
}
