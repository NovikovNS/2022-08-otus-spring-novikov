package ru.otus.homework12.service;

import ru.otus.homework12.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(long bookId);
    void updateBook(BookDto book);
    void deleteBookById(long bookId);
    BookDto createBook(BookDto book);
}
