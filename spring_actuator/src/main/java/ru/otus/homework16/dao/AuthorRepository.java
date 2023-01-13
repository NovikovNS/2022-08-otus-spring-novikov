package ru.otus.homework16.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework16.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String authorName);
}
