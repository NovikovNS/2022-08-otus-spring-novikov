package ru.otus.homework6.service;

import ru.otus.homework6.domain.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> getCommentsByBookId(int bookId);
    void saveNewComment(int bookId);
}
