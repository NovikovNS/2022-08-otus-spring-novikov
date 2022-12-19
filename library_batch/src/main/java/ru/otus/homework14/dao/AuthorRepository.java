package ru.otus.homework14.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String authorName);
}
