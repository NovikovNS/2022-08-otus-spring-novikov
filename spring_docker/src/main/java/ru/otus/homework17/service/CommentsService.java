package ru.otus.homework17.service;

import ru.otus.homework17.rest.dto.CommentDto;

import java.util.List;

public interface CommentsService {
    CommentDto getCommentById(long commentId);
    List<CommentDto> getCommentsByBookId(long bookId);
    void saveNewComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(long commentId);
}
