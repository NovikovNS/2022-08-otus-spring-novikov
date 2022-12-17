package ru.otus.homework13.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework13.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String authorName);
}
