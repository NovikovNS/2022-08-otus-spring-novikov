package ru.otus.finalProject.rest.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.finalProject.domain.Author;
import ru.otus.finalProject.domain.Wish3;
import ru.otus.finalProject.domain.Comment;
import ru.otus.finalProject.domain.Style;
import ru.otus.finalProject.rest.dto.AuthorDto;
import ru.otus.finalProject.rest.dto.BookDto;
import ru.otus.finalProject.rest.dto.CommentDto;
import ru.otus.finalProject.rest.dto.StyleDto;

@Component
public class BookDtoConverter implements DtoConverter<Wish3, BookDto> {

    private final DtoConverter<Author, AuthorDto> authorDtoConverter;
    private final DtoConverter<Style, StyleDto> styleDtoConverter;

    public BookDtoConverter(DtoConverter<Author, AuthorDto> authorDtoConverter,
                            DtoConverter<Style, StyleDto> styleDtoConverter,
                            DtoConverter<Comment, CommentDto> commentDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
        this.styleDtoConverter = styleDtoConverter;
    }

    @Override
    public BookDto mapToDto(Wish3 entity) {
        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .author(authorDtoConverter.mapToDto(entity.getAuthor()))
                .style(styleDtoConverter.mapToDto(entity.getStyle()))
                .build();
    }

    @Override
    public Wish3 mapToEntity(BookDto dto) {
        return Wish3.builder()
                .id(dto.getId())
                .name(dto.getName())
                .author(authorDtoConverter.mapToEntity(dto.getAuthor()))
                .style(styleDtoConverter.mapToEntity(dto.getStyle()))
                .comments(null)
                .build();
    }
}
