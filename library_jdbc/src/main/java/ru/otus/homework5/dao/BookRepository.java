package ru.otus.homework5.dao;

import ru.otus.homework5.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAllBooks();
    Book getBookById(int bookId);
    long saveNewBook(Book book);
    void updateBook(Book book);
    void deleteBookById(int bookId);
}
