package ru.otus.homework11.rest.dto.converter;

import org.springframework.stereotype.Component;
import ru.otus.homework11.domain.Comment;
import ru.otus.homework11.rest.dto.CommentDto;

@Component
public class CommentDtoConverter implements DtoConverter<Comment, CommentDto> {
    @Override
    public CommentDto mapToDto(Comment entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .build();
    }

    @Override
    public Comment mapToEntity(CommentDto dto) {
        return Comment.builder()
                .id(dto.getId())
                .comment(dto.getComment())
                .build();
    }
}
