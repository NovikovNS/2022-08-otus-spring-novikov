package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
