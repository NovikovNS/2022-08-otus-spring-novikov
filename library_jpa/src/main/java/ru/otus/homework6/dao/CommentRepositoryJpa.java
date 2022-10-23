package ru.otus.homework6.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
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
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            return book.getComments();
        } else return List.of();
    }

    @Override
    public void save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
        } else em.merge(comment);
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
