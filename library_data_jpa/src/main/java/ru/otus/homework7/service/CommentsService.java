package ru.otus.homework7.service;

import ru.otus.homework7.domain.Comment;

import java.util.List;

public interface CommentsService {
    Comment getCommentById(long commentId);
    List<Comment> getCommentsByBookId(long bookId);
    void saveNewComment(long bookId);
    void updateComment(long commentId, String comment);
    void deleteComment(long commentId);
}
