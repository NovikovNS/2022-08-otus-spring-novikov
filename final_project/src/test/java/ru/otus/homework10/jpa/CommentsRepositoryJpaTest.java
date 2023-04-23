package ru.otus.homework10.jpa;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.finalProject.dao.CommentRepository;
import ru.otus.finalProject.domain.Comment;

import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentsRepositoryJpaTest {

    @Autowired
    CommentRepository commentJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindCommentById() {
        val commentId = 1L;
        val jpaComment = commentJpa.getReferenceById(commentId);
        val expectedComment = entityManager.find(Comment.class, commentId);
        assertThat(jpaComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldSaveNewComment() {
        val comment = Comment.builder().comment("New comment").bookId(1).build();
        commentJpa.save(comment);
        TypedQuery<Comment> query = entityManager.getEntityManager()
                .createQuery("select c from Comment c", Comment.class);
        val expectedCommentNumber = query.getResultList().size();
        Assertions.assertEquals(3, expectedCommentNumber);
    }

    @Test
    void shouldUpdateComment() {
        val commentId = 1L;
        val oldTextComment = commentJpa.getReferenceById(commentId).getComment();
        val newTextComment = "New comment";
        val testComment = Comment.builder().id(commentId).comment(newTextComment).build();
        commentJpa.save(testComment);
        val updatedText = commentJpa.getReferenceById(commentId).getComment();
        Assertions.assertNotEquals(oldTextComment, updatedText);
        Assertions.assertEquals(newTextComment, updatedText);
    }

    @Test
    void shouldDeleteComment() {
        commentJpa.deleteById(1L);
        TypedQuery<Comment> query = entityManager.getEntityManager()
                .createQuery("select c from Comment c", Comment.class);
        val expectedCommentNumber = query.getResultList().size();
        Assertions.assertEquals(1, expectedCommentNumber);
    }

}