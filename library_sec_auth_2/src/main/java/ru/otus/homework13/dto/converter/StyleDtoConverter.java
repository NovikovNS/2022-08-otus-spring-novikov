package ru.otus.homework13.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework13.domain.Style;
import ru.otus.homework13.dto.StyleDto;

@Component
public class StyleDtoConverter implements DtoConverter<Style, StyleDto> {

    @Override
    public StyleDto mapToDto(Style entity) {
        return StyleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Style mapToEntity(StyleDto dto) {
        return Style.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
