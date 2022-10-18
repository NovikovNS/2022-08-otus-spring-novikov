package ru.otus.homework6.dao;

import org.springframework.stereotype.Repository;
import ru.otus.homework6.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentsRepositoryJpa implements CommentsRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> getCommentsByBookId(int bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.bookId=:bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public void saveNewComment(Comment comment) {
        em.merge(comment);
    }

    @Override
    public void updateComment(int commentId) {

    }

    @Override
    public void deleteComment(int commentId) {

    }
}
