package ru.otus.finalProject.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.finalProject.domain.Wish3;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Wish3, Long> {
    @EntityGraph(value = "book-author-style-entity-graph")
    List<Wish3> findAll();

    @EntityGraph(attributePaths = {"author", "style"})
    Optional<Wish3> findBookWithAuthorAndStyleById(long bookId);
}
