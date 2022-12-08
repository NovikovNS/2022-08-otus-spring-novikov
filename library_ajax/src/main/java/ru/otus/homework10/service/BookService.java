package ru.otus.homework10.service;

import ru.otus.homework10.rest.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(long bookId);
    void updateBook(BookDto book);
    void deleteBookById(long bookId);
    BookDto createBook(BookDto book);
}
