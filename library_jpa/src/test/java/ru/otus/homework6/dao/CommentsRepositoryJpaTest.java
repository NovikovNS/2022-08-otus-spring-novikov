package ru.otus.homework6.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@Import(CommentRepositoryJpa.class)
@DataJpaTest
class CommentsRepositoryJpaTest {

    @Autowired
    CommentRepositoryJpa commentJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindCommentById() {
        //TODO
    }

    @Test
    void shouldSaveNewComment() {
        //TODO
    }

    @Test
    void shouldUpdateComment() {
        //TODO
    }

    @Test
    void shouldDeleteComment() {
        //TODO
    }

}