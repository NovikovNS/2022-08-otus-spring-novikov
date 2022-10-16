package ru.otus.homework6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework6.dao.StyleRepository;
import ru.otus.homework6.domain.Style;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public Style getStyleById(int styleId) {
        return styleRepository.getStyleById(styleId);
    }

    @Override
    public Style getStyleByName(String styleName) {
        return styleRepository.getStyleByName(styleName).orElse(
                styleRepository.getStyleById(styleRepository.saveNewStyle(
                        Style.builder().name(styleName).build())));
        }
}

