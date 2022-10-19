package ru.otus.homework6.dao;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.domain.Comment;

import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;

@Import(CommentRepositoryJpa.class)
@DataJpaTest
class CommentsRepositoryJpaTest {

    @Autowired
    CommentRepositoryJpa commentJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindCommentById() {
        val commentId = 1;
        val jpaComment = commentJpa.getCommentById(commentId);
        val expectedComment = entityManager.find(Comment.class, commentId);
        assertThat(jpaComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldSaveNewComment() {
        val comment = Comment.builder().comment("New comment").bookId(1).build();
        commentJpa.saveNewComment(comment);
        TypedQuery<Comment> query = entityManager.getEntityManager()
                .createQuery("select c from Comment c", Comment.class);
        val expectedCommentNumber = query.getResultList().size();
        Assertions.assertEquals(3, expectedCommentNumber);
    }

    @Test
    void shouldUpdateComment() {
        val commentId = 1;
        val comment = commentJpa.getCommentById(commentId);
        val newText = "New comment";
        commentJpa.updateComment(commentId, newText);
        entityManager.detach(comment);
        val updatedText = commentJpa.getCommentById(commentId).getComment();
        Assertions.assertNotEquals(comment.getComment(), updatedText);
        Assertions.assertEquals(newText, updatedText);
    }

    @Test
    void shouldDeleteComment() {
        commentJpa.deleteComment(1);
        TypedQuery<Comment> query = entityManager.getEntityManager()
                .createQuery("select c from Comment c", Comment.class);
        val expectedCommentNumber = query.getResultList().size();
        Assertions.assertEquals(1, expectedCommentNumber);
    }

}