package ru.otus.homework11.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework11.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
