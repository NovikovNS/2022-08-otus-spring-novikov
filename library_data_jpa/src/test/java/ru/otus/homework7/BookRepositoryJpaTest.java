package ru.otus.homework7;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework7.dao.BookRepositoryJpa;
import ru.otus.homework7.domain.Author;
import ru.otus.homework7.domain.Book;
import ru.otus.homework7.domain.Comment;
import ru.otus.homework7.domain.Style;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BookRepositoryJpa.class)
@DataJpaTest
class BookRepositoryJpaTest {

    private static final int EXPECTED_QUERIES_COUNT_ALL_BOOKS = 1;
    private static final int EXPECTED_LIBRARY_SIZE = 2;

    @Autowired
    BookRepositoryJpa bookJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindAllBooks() {
        SessionFactory sessionFactory = entityManager.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        Assertions.assertEquals(EXPECTED_LIBRARY_SIZE, bookJpa.findAllBooks().size());
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
        Assertions.assertEquals(testBook, bookJpa.getBookById(1).get());
    }

    @Test
    void testFindBookByIdJpa(){
        val optionalBook = bookJpa.getBookById(1);
        val expectedBook = entityManager.find(Book.class, 1);
        assertThat(optionalBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldDeleteBookById() {
        bookJpa.deleteBookById(1);
        Assertions.assertEquals(1, bookJpa.findAllBooks().size());
    }

    @Test
    void shouldCreateNewBook() {
        val testBook = Book.builder().name("Book").author(Author.builder().id(1).name("testName").build())
                .style(Style.builder().id(1).name("testStyle").build()).build();
        bookJpa.saveNewBook(testBook);
        Assertions.assertEquals(3, bookJpa.findAllBooks().size());
    }
}