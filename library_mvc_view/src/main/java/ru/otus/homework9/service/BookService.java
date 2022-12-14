package ru.otus.homework9.service;

import ru.otus.homework9.dto.BookDto;
import ru.otus.homework9.dto.CreatingBookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(long bookId);
    void updateBook(BookDto book);
    void deleteBookById(long bookId);
    BookDto createBook(BookDto book);
}
