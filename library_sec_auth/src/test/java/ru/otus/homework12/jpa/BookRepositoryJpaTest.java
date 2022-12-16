package ru.otus.homework12.jpa;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework12.dao.BookRepository;
import ru.otus.homework12.domain.Author;
import ru.otus.homework12.domain.Book;
import ru.otus.homework12.domain.Comment;
import ru.otus.homework12.domain.Style;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryJpaTest {

    private static final int EXPECTED_QUERIES_COUNT_ALL_BOOKS = 1;
    private static final int EXPECTED_LIBRARY_SIZE = 2;

    @Autowired
    BookRepository bookJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindAllBooks() {
        SessionFactory sessionFactory = entityManager.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        Assertions.assertEquals(EXPECTED_LIBRARY_SIZE, bookJpa.findAll().size());
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT_ALL_BOOKS);
    }

    @Test
    void shouldFindBookById() {
        val testBook = Book.builder()
                .id(1)
                .name("Евгений Онегин")
                .author(Author.builder().id(1).name("Пушкин").build())
                .style(Style.builder().id(1).name("Роман").build())
                .comments(List.of(Comment.builder().id(1).comment("Неплохая книжка").bookId(1).build()))
                .build();
        Assertions.assertEquals(testBook, bookJpa.findBookById(1).get());
    }

    @Test
    void testFindBookByIdJpa(){
        val optionalBook = bookJpa.findBookById(1);
        val expectedBook = entityManager.find(Book.class, 1L);
        assertThat(optionalBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldDeleteBookById() {
        bookJpa.deleteById(1L);
        Assertions.assertEquals(1, bookJpa.findAll().size());
    }

    @Test
    void shouldCreateNewBook() {
        val testBook = Book.builder().name("Book").author(Author.builder().id(1).name("testName").build())
                .style(Style.builder().id(1).name("testStyle").build()).build();
        bookJpa.save(testBook);
        Assertions.assertEquals(3, bookJpa.findAll().size());
    }
}