package ru.otus.homework7.service;

import ru.otus.homework7.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(long bookId);
    Book saveNewBook(Book book);
    Book updateBook(long bookId);
    void deleteBookById(long bookId);

    Book createBook();
}
