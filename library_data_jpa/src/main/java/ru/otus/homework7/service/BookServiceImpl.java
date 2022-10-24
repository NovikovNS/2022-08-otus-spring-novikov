package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao.BookRepository;
import ru.otus.homework7.domain.Author;
import ru.otus.homework7.domain.Book;
import ru.otus.homework7.domain.Style;
import ru.otus.homework7.dto.BookDto;
import ru.otus.homework7.dto.converter.DtoConverter;
import ru.otus.homework7.exception.BookNotFoundException;

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
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookDtoConverter::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookById(long bookId) {
        return bookDtoConverter.mapToDto(bookRepository.findBookById(bookId)
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
        Book bookEntity = bookRepository.findBookById(book.getId())
                .orElseThrow(() -> new BookNotFoundException(String.format("Not found book with bookId:%s", book.getId())));

        Author author = authorService.getAuthorByName(book.getAuthor().getName());
        Style style = styleService.getStyleByName(book.getStyle().getName());

        bookEntity.setName(book.getName());
        bookEntity.setAuthor(author);
        bookEntity.setStyle(style);
    }

    @Override
    @Transactional
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }

}
