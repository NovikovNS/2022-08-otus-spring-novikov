package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment getCommentById(int commentId);
    List<Comment> getCommentsByBookId(int bookId);
    void save(Comment comment);
    void deleteComment(int commentId);
}
