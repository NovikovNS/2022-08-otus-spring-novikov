package ru.otus.homework5.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework5.domain.Author;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.domain.Style;

@Import(BookRepositoryJdbc.class)
@JdbcTest
class BookRepositoryJdbcTest {

    @Autowired
    BookRepositoryJdbc bookJdbc;

    @Test
    void findAllBooks() {
        Assertions.assertEquals(2, bookJdbc.findAllBooks().size());
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
        Assertions.assertEquals(testBook, bookJdbc.getBookById(1));
    }

    @Test
    void testDeletingBookById() {
        bookJdbc.deleteBookById(1);
        Assertions.assertEquals(1, bookJdbc.findAllBooks().size());
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
        bookJdbc.saveNewBook(testBook);
        Assertions.assertEquals(3, bookJdbc.findAllBooks().size());
    }
}