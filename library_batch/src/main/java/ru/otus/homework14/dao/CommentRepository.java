package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.nosql.CommentNoSql;

public interface CommentRepository extends MongoRepository<CommentNoSql, String> {
}
