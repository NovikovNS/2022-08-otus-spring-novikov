package ru.otus.homework7.service;

import ru.otus.homework7.domain.Comment;

import java.util.List;

public interface CommentsService {
    Comment getCommentById(int commentId);
    List<Comment> getCommentsByBookId(int bookId);
    void saveNewComment(int bookId);
    void updateComment(int commentId, String comment);
    void deleteComment(int commentId);
}
