package ru.otus.homework5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.domain.Author;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.domain.Style;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public List<Book> findAllBooks() {
        return jdbcOperations.query("select b.id, b.name, b.author_id, a.name author_name, b.style_id, s.name style_name " +
                "from (books b left join authors a on b.author_id = a.id)" +
                "left join styles s on s.id = b.style_id", new BookMapper());
    }

    @Override
    public Book getBookById(int bookId) {
        Map<String, Object> params = Collections.singletonMap("id", bookId);
        return jdbcOperations.queryForObject("select b.id, b.name, b.author_id, a.name author_name, b.style_id, s.name style_name " +
                        "from (books b left join authors a on b.author_id = a.id)" +
                        "left join styles s on s.id = b.style_id where b.id = :id", params, new BookMapper());
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
        Map<String, Object> params = Collections.singletonMap("id", bookId);
        jdbcOperations.update(
                "delete from books where id = :id", params
        );
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
