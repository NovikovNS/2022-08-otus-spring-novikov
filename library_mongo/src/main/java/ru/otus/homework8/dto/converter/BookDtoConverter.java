package ru.otus.homework8.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework8.domain.Author;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Comment;
import ru.otus.homework8.domain.Style;
import ru.otus.homework8.dto.AuthorDto;
import ru.otus.homework8.dto.BookDto;
import ru.otus.homework8.dto.CommentDto;
import ru.otus.homework8.dto.StyleDto;

@Component
public class BookDtoConverter implements DtoConverter<Book, BookDto> {

    private final DtoConverter<Author, AuthorDto> authorDtoConverter;
    private final DtoConverter<Style, StyleDto> styleDtoConverter;

    public BookDtoConverter(DtoConverter<Author, AuthorDto> authorDtoConverter,
                            DtoConverter<Style, StyleDto> styleDtoConverter,
                            DtoConverter<Comment, CommentDto> commentDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
        this.styleDtoConverter = styleDtoConverter;
    }

    @Override
    public BookDto mapToDto(Book entity) {
        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .author(authorDtoConverter.mapToDto(entity.getAuthor()))
                .style(styleDtoConverter.mapToDto(entity.getStyle()))
                .build();
    }

    @Override
    public Book mapToEntity(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .name(dto.getName())
                .author(authorDtoConverter.mapToEntity(dto.getAuthor()))
                .style(styleDtoConverter.mapToEntity(dto.getStyle()))
                .comments(null)
                .build();
    }
}
