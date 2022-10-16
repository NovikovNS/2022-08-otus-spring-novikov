package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Style;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    private final NamedParameterJdbcOperations jdbcOperations;

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
    public long saveNewBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of(
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "style_id", book.getStyle().getId()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into books (name, author_id, style_id) " +
                "values (:name, :author_id, :style_id)", params, keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void updateBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "style_id", book.getStyle().getId()));
        jdbcOperations.update("update books set name=:name, author_id=:author_id, style_id=:style_id where id=:id", params);
    }

    @Override
    public void deleteBookById(int bookId) {
        Query query = em.createQuery("delete from Book b where b.id=:bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Book.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .author(Author.builder()
                            .id(resultSet.getLong("author_id"))
                            .name(resultSet.getString("author_name"))
                            .build())
                    .style(Style.builder()
                            .id(resultSet.getLong("style_id"))
                            .name(resultSet.getString("style_name"))
                            .build())
                    .build();
        }
    }
}
