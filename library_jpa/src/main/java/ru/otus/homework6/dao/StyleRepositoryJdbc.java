package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework6.domain.Style;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StyleRepositoryJdbc implements StyleRepository {

    @PersistenceContext
    private EntityManager em;

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Style getStyleById(long styleId) {
        return em.find(Style.class, styleId);
    }

    @Override
    public Optional<Style> getStyleByName(String styleName) {
        try {
            TypedQuery<Style> query = em.createQuery("select s from Style s where s.name=:styleName", Style.class);
            query.setParameter("styleName", styleName);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public long saveNewStyle(Style style) {
        return em.merge(style).getId();
    }
}
