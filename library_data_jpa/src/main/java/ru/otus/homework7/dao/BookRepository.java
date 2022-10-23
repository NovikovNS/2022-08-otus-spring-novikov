package ru.otus.homework7.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework7.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-author-style-entity-graph")
    List<Book> findAll();

    @EntityGraph(value = "book-author-style-entity-graph")
    Book findBookById(long bookId);

    //TODO неоптимизированное удаление
    void deleteBookById(long bookId);
}
