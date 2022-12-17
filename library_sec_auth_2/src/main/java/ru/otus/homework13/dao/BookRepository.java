package ru.otus.homework13.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework13.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-author-style-entity-graph")
    List<Book> findAll();

    @EntityGraph(value = "book-author-style-entity-graph")
    Optional<Book> findBookById(long bookId);
}
