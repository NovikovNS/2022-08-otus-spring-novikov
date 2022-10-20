package ru.otus.homework7;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework7.dao.AuthorRepositoryJpa;
import ru.otus.homework7.domain.Author;

import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;

@Import(AuthorRepositoryJpa.class)
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    AuthorRepositoryJpa authorJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindAuthorById() {
        val authorId = 1;
        val jpaAuthor = authorJpa.getAuthorById(authorId);
        val expectedAuthor = entityManager.find(Author.class, authorId);
        assertThat(jpaAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldFindAuthorByName() {
        val testAuthor = Author.builder().id(1).name("Пушкин").build();
        val jpaAuthor = authorJpa.getAuthorByName(testAuthor.getName());
        assertThat(jpaAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(testAuthor);
    }

    @Test
    void shouldSaveNewAuthor() {
        val testAuthor = Author.builder().name("New").build();
        authorJpa.saveNewAuthor(testAuthor);
        TypedQuery<Author> query = entityManager.getEntityManager().createQuery("select a from Author a", Author.class);
        val expectedAuthorNumber = query.getResultList().size();
        Assertions.assertEquals(3, expectedAuthorNumber);
    }

}