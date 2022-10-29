package ru.otus.homework6.dao;


import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.domain.Style;

import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;

@Import(StyleRepositoryJpa.class)
@DataJpaTest
public class StyleRepositoryJpaTest {

    @Autowired
    StyleRepositoryJpa styleJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindStyleById() {
        val styleId = 1;
        val jpaStyle = styleJpa.getStyleById(styleId);
        val expectedStyle = entityManager.find(Style.class, styleId);
        assertThat(jpaStyle).usingRecursiveComparison().isEqualTo(expectedStyle);

    }

    @Test
    void shouldFindStyleByName() {
        val testStyle = Style.builder().id(1).name("Роман").build();
        val jpaStyle = styleJpa.getStyleByName(testStyle.getName());
        assertThat(jpaStyle).isPresent().get().usingRecursiveComparison().isEqualTo(testStyle);
    }

    @Test
    void shouldSaveNewStyle() {
        val testStyle = Style.builder().name("New").build();
        styleJpa.saveNewStyle(testStyle);
        TypedQuery<Style> query = entityManager.getEntityManager().createQuery("select s from Style s", Style.class);
        val expectedStyleNumber = query.getResultList().size();
        Assertions.assertEquals(3, expectedStyleNumber);
    }
}
