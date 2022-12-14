package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.StyleRepository;
import ru.otus.homework6.domain.Style;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public Style getStyleById(int styleId) {
        return styleRepository.getStyleById(styleId);
    }

    @Override
    @Transactional
    public Style getStyleByName(String styleName) {
        return styleRepository.getStyleByName(styleName).orElse(
                styleRepository.getStyleById(styleRepository.saveNewStyle(
                        Style.builder().name(styleName).build())));
        }
}

