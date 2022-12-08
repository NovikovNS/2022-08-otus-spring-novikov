package ru.otus.homework11.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework11.domain.Style;

import java.util.Optional;

public interface StyleRepository extends ReactiveMongoRepository<Style, String> {
    Optional<Style> findByName(String styleName);
}
