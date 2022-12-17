package ru.otus.homework13.jpa;


import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework13.dao.StyleRepository;
import ru.otus.homework13.domain.Style;

import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StyleRepositoryJpaTest {

    @Autowired
    StyleRepository styleJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindStyleById() {
        val styleId = 1L;
        val jpaStyle = styleJpa.findById(styleId).get();
        val expectedStyle = entityManager.find(Style.class, styleId);
        assertThat(jpaStyle).usingRecursiveComparison().isEqualTo(expectedStyle);

    }

    @Test
    void shouldFindStyleByName() {
        val testStyle = Style.builder().id(1).name("Роман").build();
        val jpaStyle = styleJpa.findByName(testStyle.getName());
        assertThat(jpaStyle).isPresent().get().usingRecursiveComparison().isEqualTo(testStyle);
    }

    @Test
    void shouldSaveNewStyle() {
        val testStyle = Style.builder().name("New").build();
        styleJpa.save(testStyle);
        TypedQuery<Style> query = entityManager.getEntityManager().createQuery("select s from Style s", Style.class);
        val expectedStyleNumber = query.getResultList().size();
        Assertions.assertEquals(3, expectedStyleNumber);
    }
}
