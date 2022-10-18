package ru.otus.homework6.dao;

import ru.otus.homework6.domain.Comment;

import java.util.List;

public interface CommentsRepository {
    List<Comment> getCommentsByBookId(int bookId);
    void saveNewComment(Comment comment);
    void updateComment(int commentId);
    void deleteComment(int commentId);
}
