package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Style;

import java.util.Optional;

public interface StyleRepository {
    Style getStyleById(long styleId);
    Optional<Style> getStyleByName(String styleName);
    long saveNewStyle(Style style);
}
