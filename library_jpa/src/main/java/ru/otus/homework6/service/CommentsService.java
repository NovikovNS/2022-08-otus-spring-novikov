package ru.otus.homework6.service;

import ru.otus.homework6.domain.Comment;
import ru.otus.homework6.dto.CommentDto;

import java.util.List;

public interface CommentsService {
    Comment getCommentById(int commentId);
    List<CommentDto> getCommentsByBookId(int bookId);
    void saveNewComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(int commentId);
}
