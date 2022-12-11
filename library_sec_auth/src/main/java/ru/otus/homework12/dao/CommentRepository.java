package ru.otus.homework12.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework12.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByBookId(long bookId);
}
