package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Style;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StyleRepositoryJpa implements StyleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Style getStyleById(int styleId) {
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
    public int saveNewStyle(Style style) {
        return em.merge(style).getId();
    }
}
