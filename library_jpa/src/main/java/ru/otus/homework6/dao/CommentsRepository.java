package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Comment;

import java.util.List;

public interface CommentsRepository {
    Comment getCommentById(int commentId);
    List<Comment> getCommentsByBookId(int bookId);
    void saveNewComment(Comment comment);
    void updateComment(int commentId, String comment);
    void deleteComment(int commentId);
}
