package ru.otus.homework12.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework12.domain.Author;
import ru.otus.homework12.dto.AuthorDto;

@Component
public class AuthorDtoConverter implements DtoConverter<Author, AuthorDto> {

    @Override
    public AuthorDto mapToDto(Author entity) {
        return AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Author mapToEntity(AuthorDto dto) {
        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
