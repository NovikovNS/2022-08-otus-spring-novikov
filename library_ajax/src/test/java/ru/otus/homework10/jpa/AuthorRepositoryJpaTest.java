package ru.otus.homework10.jpa;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework10.dao.AuthorRepository;
import ru.otus.homework10.domain.Author;

import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    AuthorRepository authorJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindAuthorById() {
        val authorId = 1L;
        val jpaAuthor = authorJpa.findById(authorId).get();
        val expectedAuthor = entityManager.find(Author.class, authorId);
        assertThat(jpaAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldFindAuthorByName() {
        val testAuthor = Author.builder().id(1).name("Пушкин").build();
        val jpaAuthor = authorJpa.findByName(testAuthor.getName());
        assertThat(jpaAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(testAuthor);
    }

    @Test
    void shouldSaveNewAuthor() {
        val testAuthor = Author.builder().name("New").build();
        authorJpa.save(testAuthor);
        TypedQuery<Author> query = entityManager.getEntityManager().createQuery("select a from Author a", Author.class);
        val expectedAuthorNumber = query.getResultList().size();
        Assertions.assertEquals(3, expectedAuthorNumber);
    }

}