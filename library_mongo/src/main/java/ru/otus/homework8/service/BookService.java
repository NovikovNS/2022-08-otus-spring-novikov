package ru.otus.homework8.service;

import ru.otus.homework8.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(String bookId);
    void updateBook(BookDto book);
    void deleteBookById(String bookId);
    BookDto createBook(BookDto book);
}
