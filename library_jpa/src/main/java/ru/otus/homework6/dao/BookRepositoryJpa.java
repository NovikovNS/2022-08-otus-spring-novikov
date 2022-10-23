package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAllBooks() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-style-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getBookById(int bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    public Book save(Book book) {
        return em.merge(book);
    }

    @Override
    public void deleteBookById(int bookId) {
        getBookById(bookId).map(book -> {
            em.remove(book);
            return true;
        }).orElseThrow();
    }
}
