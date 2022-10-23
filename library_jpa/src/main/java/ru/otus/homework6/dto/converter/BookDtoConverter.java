package ru.otus.homework6.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Comment;
import ru.otus.homework6.domain.Style;
import ru.otus.homework6.dto.AuthorDto;
import ru.otus.homework6.dto.BookDto;
import ru.otus.homework6.dto.CommentDto;
import ru.otus.homework6.dto.StyleDto;

@Component
public class BookDtoConverter implements DtoConverter<Book, BookDto> {

    private final DtoConverter<Author, AuthorDto> authorDtoConverter;
    private final DtoConverter<Style, StyleDto> styleDtoConverter;
    private final DtoConverter<Comment, CommentDto> commentDtoConverter;

    public BookDtoConverter(DtoConverter<Author, AuthorDto> authorDtoConverter,
                            DtoConverter<Style, StyleDto> styleDtoConverter,
                            DtoConverter<Comment, CommentDto> commentDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
        this.styleDtoConverter = styleDtoConverter;
        this.commentDtoConverter = commentDtoConverter;
    }

    @Override
    public BookDto toDto(Book entity) {
        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .author(authorDtoConverter.toDto(entity.getAuthor()))
                .style(styleDtoConverter.toDto(entity.getStyle()))
                .build();
    }

    @Override
    public Book fromDto(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .name(dto.getName())
                .author(authorDtoConverter.fromDto(dto.getAuthor()))
                .style(styleDtoConverter.fromDto(dto.getStyle()))
                .comments(null)
                .build();
    }
}
