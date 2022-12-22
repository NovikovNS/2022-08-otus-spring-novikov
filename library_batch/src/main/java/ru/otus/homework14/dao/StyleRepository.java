package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.nosql.StyleNoSql;

import java.util.Optional;

public interface StyleRepository extends MongoRepository<StyleNoSql, String> {
    Optional<StyleNoSql> findByName(String styleName);
}
