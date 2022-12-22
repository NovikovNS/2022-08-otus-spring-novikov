package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.nosql.AuthorNoSql;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<AuthorNoSql, String> {
    Optional<AuthorNoSql> findByName(String authorName);
}
