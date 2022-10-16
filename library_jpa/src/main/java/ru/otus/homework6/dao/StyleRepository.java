package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Style;

public interface StyleRepository {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    long saveNewStyle(String styleName);
    boolean checkStyleByName(String styleName);
}
