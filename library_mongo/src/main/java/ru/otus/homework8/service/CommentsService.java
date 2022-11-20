package ru.otus.homework8.service;

import ru.otus.homework8.dto.CommentDto;

import java.util.List;

public interface CommentsService {
    CommentDto getCommentById(String commentId);
    List<CommentDto> getCommentsByBookId(String bookId);
    void saveNewComment(CommentDto commentDto, String bookId);
    void updateComment(CommentDto commentDto);
    void deleteComment(String commentId);
}
