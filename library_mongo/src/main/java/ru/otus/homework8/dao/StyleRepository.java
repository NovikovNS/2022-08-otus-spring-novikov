package ru.otus.homework8.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.homework8.domain.Style;

import java.util.Optional;

public interface StyleRepository extends MongoRepository<Style, String> {
    Optional<Style> findByName(String styleName);
}
