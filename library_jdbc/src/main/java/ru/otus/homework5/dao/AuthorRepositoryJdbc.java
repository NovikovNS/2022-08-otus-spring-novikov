package ru.otus.homework5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJdbc implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Author getAuthorById(long authorId) {
        Map<String, Object> params = Collections.singletonMap("id", authorId);
        return jdbcOperations.queryForObject("select a.id, a.name from authors a where a.id=:id", params, new AuthorMapper());
    }

    @Override
    public Author getAuthorByName(String authorName) {
        Map<String, Object> params = Collections.singletonMap("name", authorName);
        return jdbcOperations.queryForObject("select a.id, a.name from authors a where a.name=:name", params, new AuthorMapper());
    }

    @Override
    public long saveNewAuthor(String authorName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of(
                "name", authorName));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into authors (name) values (:name)", params, keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean checkAuthorByName(String authorName) {
        Map<String, Object> params = Collections.singletonMap("name", authorName);
        List<Author> authorsList = jdbcOperations.query("select s.id, s.name from authors s where s.name=:name", params, new AuthorMapper());
        return authorsList.size() != 0;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Author.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
    }
}
