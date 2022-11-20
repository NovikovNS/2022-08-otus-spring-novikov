package ru.otus.homework9.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework9.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByBookId(long bookId);
}
