package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.BookRepository;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Style;
import ru.otus.homework6.dto.BookDto;
import ru.otus.homework6.dto.converter.DtoConverter;
import ru.otus.homework6.exception.BookNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final StyleService styleService;
    private final DtoConverter<Book, BookDto> bookDtoConverter;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           StyleService styleService,
                           DtoConverter<Book, BookDto> bookDtoConverter) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.styleService = styleService;
        this.bookDtoConverter = bookDtoConverter;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAllBooks().stream().map(bookDtoConverter::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(int bookId) {
        return bookDtoConverter.mapToDto(bookRepository.getBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Not found book with bookId:%s", bookId))));
    }

    @Override
    @Transactional
    public BookDto createBook(BookDto book) {
        Author author = authorService.getAuthorByName(book.getAuthor().getName());
        Style style = styleService.getStyleByName(book.getStyle().getName());
        return bookDtoConverter.mapToDto(bookRepository.save(Book.builder()
                .name(book.getName())
                .author(author)
                .style(style)
                .build()));
    }

    @Override
    @Transactional
    public void updateBook(BookDto book) {
        Book bookEntity = bookRepository.getBookById(book.getId())
                .orElseThrow(() -> new BookNotFoundException(String.format("Not found book with bookId:%s", book.getId())));

        Author author = authorService.getAuthorByName(book.getAuthor().getName());
        Style style = styleService.getStyleByName(book.getStyle().getName());

        bookEntity.setName(book.getName());
        bookEntity.setAuthor(author);
        bookEntity.setStyle(style);
    }

    @Override
    @Transactional
    public void deleteBookById(int bookId) {
        bookRepository.deleteBookById(bookId);
    }

}
