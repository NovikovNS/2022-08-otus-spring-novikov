package ru.otus.homework5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.domain.Style;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class StyleRepositoryJdbc implements StyleRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Style getStyleById(long styleId) {
        Map<String, Object> params = Collections.singletonMap("id", styleId);
        return jdbcOperations.queryForObject("select s.id, s.name from styles s where s.id=:id", params, new StyleMapper());
    }

    @Override
    public Style getStyleByName(String styleName) {
        Map<String, Object> params = Collections.singletonMap("name", styleName);
        return jdbcOperations.queryForObject("select s.id, s.name from styles s where s.name=:name", params, new StyleMapper());
    }

    @Override
    public long saveNewStyle(String styleName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of(
                "name", styleName));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into styles (name) values (:name)", params, keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean checkStyleByName(String styleName) {
        Map<String, Object> params = Collections.singletonMap("name", styleName);
        List<Style> stylesList = jdbcOperations.query("select s.id, s.name from styles s where s.name=:name", params, new StyleMapper());
        return stylesList.size() != 0;
    }

    private static class StyleMapper implements RowMapper<Style> {
        @Override
        public Style mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return Style.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
    }
}
