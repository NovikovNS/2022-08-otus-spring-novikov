package ru.otus.homework7.service;

import ru.otus.homework7.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    long saveNewBook(Book book);
    void updateBook(int bookId);
    void deleteBookById(int bookId);

    Book createBook();
}
