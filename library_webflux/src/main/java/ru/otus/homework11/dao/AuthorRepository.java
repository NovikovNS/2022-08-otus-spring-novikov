package ru.otus.homework11.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework11.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Optional<Author> findByName(String authorName);
}
