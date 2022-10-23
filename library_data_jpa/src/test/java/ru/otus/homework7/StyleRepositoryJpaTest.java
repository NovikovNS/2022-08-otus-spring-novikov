package ru.otus.homework7;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class StyleRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;
//
//    @Test
//    void shouldFindStyleById() {
//        val styleId = 1;
//        val jpaStyle = styleJpa.getStyleById(styleId);
//        val expectedStyle = entityManager.find(Style.class, styleId);
//        assertThat(jpaStyle).usingRecursiveComparison().isEqualTo(expectedStyle);
//
//    }
//
//    @Test
//    void shouldFindStyleByName() {
//        val testStyle = Style.builder().id(1).name("Роман").build();
//        val jpaStyle = styleJpa.getStyleByName(testStyle.getName());
//        assertThat(jpaStyle).isPresent().get().usingRecursiveComparison().isEqualTo(testStyle);
//    }
//
//    @Test
//    void shouldSaveNewStyle() {
//        val testStyle = Style.builder().name("New").build();
//        styleJpa.saveNewStyle(testStyle);
//        TypedQuery<Style> query = entityManager.getEntityManager().createQuery("select s from Style s", Style.class);
//        val expectedStyleNumber = query.getResultList().size();
//        Assertions.assertEquals(3, expectedStyleNumber);
//    }
}
