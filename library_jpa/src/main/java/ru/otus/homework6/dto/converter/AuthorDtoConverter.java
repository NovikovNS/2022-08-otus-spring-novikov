package ru.otus.homework6.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.dto.AuthorDto;

@Component
public class AuthorDtoConverter implements DtoConverter<Author, AuthorDto> {

    @Override
    public AuthorDto toDto(Author entity) {
        return AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Author fromDto(AuthorDto dto) {
        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
