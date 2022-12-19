package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.Style;

import java.util.Optional;

public interface StyleRepository extends MongoRepository<Style, String> {
    Optional<Style> findByName(String styleName);
}
