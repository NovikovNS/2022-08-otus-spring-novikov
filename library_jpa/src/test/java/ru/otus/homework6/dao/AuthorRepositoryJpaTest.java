package ru.otus.homework6.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@Import(AuthorRepositoryJpa.class)
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    AuthorRepositoryJpa authorJpa;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void shouldFindAuthorById() {
        //TODO
    }

    @Test
    void shouldFindAuthorByName() {
        //TODO
    }

    @Test
    void shouldSaveNewAuthor() {
        //TODO
    }

}