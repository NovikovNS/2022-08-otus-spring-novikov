package ru.otus.homework6.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Style;

@Import(BookRepositoryJpa.class)
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    BookRepositoryJpa bookJpa;

    @Test
    void findAllBooks() {
        Assertions.assertEquals(2, bookJpa.findAllBooks().size());
    }

    @Test
    void testGettingBookById() {
        var testBook = Book.builder()
                .id(1)
                .name("Евгений Онегин")
                .author(Author.builder()
                        .id(1L)
                        .name("Пушкин")
                        .build())
                .style(Style.builder()
                        .id(1L)
                        .name("Роман")
                        .build())
                .build();
        Assertions.assertEquals(testBook, bookJpa.getBookById(1));
    }

    @Test
    void testDeletingBookById() {
        bookJpa.deleteBookById(1);
        Assertions.assertEquals(1, bookJpa.findAllBooks().size());
    }

    @Test
    void testCreatingNewBook() {
        var testBook = Book.builder()
                .name("Book")
                .author(Author.builder()
                        .id(1L)
                        .name("testName")
                        .build())
                .style(Style.builder()
                        .id(1L)
                        .name("testStyle")
                        .build())
                .build();
        bookJpa.saveNewBook(testBook);
        Assertions.assertEquals(3, bookJpa.findAllBooks().size());
    }
}