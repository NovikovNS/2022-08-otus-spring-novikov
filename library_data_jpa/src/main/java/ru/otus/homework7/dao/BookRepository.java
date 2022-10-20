package ru.otus.homework7.dao;

import ru.otus.homework7.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAllBooks();
    Optional<Book> getBookById(int bookId);
    long saveNewBook(Book book);
    void updateBook(Book book);
    void deleteBookById(int bookId);
}
