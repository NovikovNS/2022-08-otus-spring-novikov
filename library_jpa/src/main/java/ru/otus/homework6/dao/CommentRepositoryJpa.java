package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework6.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment getCommentById(int commentId) {
        return em.find(Comment.class, commentId);
    }

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
    public void updateComment(int commentId, String comment) {
        Query query = em.createQuery("update Comment c set c.comment=:comment where c.id=:commentId");
        query.setParameter("commentId", commentId);
        query.setParameter("comment", comment);
        query.executeUpdate();
    }

    @Override
    public void deleteComment(int commentId) {
        Query query = em.createQuery("delete from Comment c where c.id=:commentId");
        query.setParameter("commentId", commentId);
        query.executeUpdate();
    }
}
