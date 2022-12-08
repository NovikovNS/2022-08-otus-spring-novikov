package ru.otus.homework10.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework10.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByBookId(long bookId);
}
