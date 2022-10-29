package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getAuthorById(int authorId) {
        return em.find(Author.class, authorId);
    }

    @Override
    public Optional<Author> getAuthorByName(String authorName) {
        try {
            TypedQuery<Author> query = em.createQuery("select a from Author a where a.name=:authorName", Author.class);
            query.setParameter("authorName", authorName);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return em.merge(author);
    }
}
