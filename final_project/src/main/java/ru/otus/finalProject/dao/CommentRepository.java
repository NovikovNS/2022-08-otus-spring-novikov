package ru.otus.finalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.finalProject.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByWishId(long wishId);
}
