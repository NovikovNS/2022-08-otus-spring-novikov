package ru.otus.homework11.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework11.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
}
