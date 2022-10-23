package ru.otus.homework6.service;

import ru.otus.homework6.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(int bookId);
    void updateBook(BookDto book);
    void deleteBookById(int bookId);
    BookDto createBook(BookDto book);
}
