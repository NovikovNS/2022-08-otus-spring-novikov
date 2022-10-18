package ru.otus.homework6.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@Import(StyleRepositoryJpa.class)
@DataJpaTest
public class StyleRepositoryJpaTest {

    @Autowired
    StyleRepositoryJpa styleJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindStyleById() {
        //TODO
    }

    @Test
    void shouldFindStyleByName() {
        //TODO
    }

    @Test
    void shouldSaveNewStyle() {
        //TODO
    }

}
