package ru.otus.finalProject.service;

import ru.otus.finalProject.rest.dto.wishlist.CommentDto;

import java.util.List;

public interface CommentsService {
    CommentDto getCommentById(long commentId);
    List<CommentDto> getCommentsByWishId(long wishId);
    CommentDto saveNewComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(long commentId);
}
