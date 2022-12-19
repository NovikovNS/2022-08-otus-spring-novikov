package ru.otus.homework14.service;

import ru.otus.homework14.domain.Style;

public interface StyleService {
    Style getStyleById(String styleId);
    Style getStyleByName(String styleName);
}
