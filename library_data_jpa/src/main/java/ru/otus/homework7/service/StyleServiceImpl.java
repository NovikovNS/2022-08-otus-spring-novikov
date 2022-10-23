package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao.StyleRepository;
import ru.otus.homework7.domain.Style;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    @Transactional
    public Style getStyleById(long styleId) {
        return styleRepository.getReferenceById(styleId);
    }

    @Override
    @Transactional
    public Style getStyleByName(String styleName) {
        return styleRepository.findByName(styleName).orElse(
                styleRepository.getReferenceById(styleRepository.save(
                        Style.builder().name(styleName).build()).getId()));
        }
}

