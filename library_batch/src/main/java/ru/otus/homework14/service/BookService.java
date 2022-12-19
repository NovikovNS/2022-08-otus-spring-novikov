package ru.otus.homework14.service;

import ru.otus.homework14.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(String bookId);
    void updateBook(BookDto book);
    void deleteBookById(String bookId);
    BookDto createBook(BookDto book);
}
