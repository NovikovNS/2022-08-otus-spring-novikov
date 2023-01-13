package ru.otus.homework16.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework16.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByBookId(long bookId);
}
