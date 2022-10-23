package ru.otus.homework6.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Comment;
import ru.otus.homework6.dto.CommentDto;

@Component
public class CommentDtoConverter implements DtoConverter<Comment, CommentDto> {
    @Override
    public CommentDto toDto(Comment entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .bookId(entity.getBookId())
                .build();
    }

    @Override
    public Comment fromDto(CommentDto dto) {
        return Comment.builder()
                .id(dto.getId())
                .comment(dto.getComment())
                .bookId(dto.getBookId())
                .build();
    }
}
