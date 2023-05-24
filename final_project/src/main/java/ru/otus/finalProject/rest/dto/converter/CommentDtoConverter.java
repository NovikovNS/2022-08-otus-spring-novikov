package ru.otus.finalProject.rest.dto.converter;

import org.springframework.stereotype.Service;
import ru.otus.finalProject.domain.Comment;
import ru.otus.finalProject.rest.dto.wishlist.CommentDto;

@Service
public class CommentDtoConverter implements DtoConverter<Comment, CommentDto> {
    @Override
    public CommentDto mapToDto(Comment entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .wishId(entity.getWishId())
                .build();
    }

    @Override
    public Comment mapToEntity(CommentDto dto) {
        return Comment.builder()
                .comment(dto.getComment())
                .wishId(dto.getWishId())
                .build();
    }
}
