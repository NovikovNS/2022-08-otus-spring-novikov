package ru.otus.homework5.dao;

import ru.otus.homework5.domain.Style;

public interface StyleRepository {
    Style getStyleById(long styleId);
    Style getStyleByName(String styleName);
    long saveNewStyle(String styleName);
    boolean checkStyleByName(String styleName);
}
