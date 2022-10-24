package ru.otus.homework7.service;

import ru.otus.homework7.dto.CommentDto;

import java.util.List;

public interface CommentsService {
    CommentDto getCommentById(long commentId);
    List<CommentDto> getCommentsByBookId(long bookId);
    void saveNewComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(long commentId);
}
