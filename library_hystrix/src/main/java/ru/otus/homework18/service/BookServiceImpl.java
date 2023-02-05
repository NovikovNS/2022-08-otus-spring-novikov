package ru.otus.homework18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework18.dao.BookRepository;
import ru.otus.homework18.domain.Author;
import ru.otus.homework18.domain.Book;
import ru.otus.homework18.domain.Style;
import ru.otus.homework18.exception.BookNotFoundException;
import ru.otus.homework18.rest.dto.BookDto;
import ru.otus.homework18.rest.dto.converter.DtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @HystrixCommand(groupKey = "bookService", fallbackMethod = "fallbackGetAllBooks", commandKey = "getAllBooks")
    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookDtoConverter::mapToDto).collect(Collectors.toList());
    }

    private List<BookDto> fallbackGetAllBooks() {
        log.debug("Method getAllBooks is working incorrectly. FallbackMethod 'fallbackGetAllBooks' has launched");
        return List.of();
    }

    @HystrixCommand(groupKey = "bookService", fallbackMethod = "fallbackGetBookById", commandKey = "getBookById")
    @Override
    public BookDto getBookById(long bookId) {
        return bookDtoConverter.mapToDto(bookRepository.findBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Not found book with bookId:%s", bookId))));
    }

    private BookDto fallbackGetBookById() {
        log.debug("Method getBookById is working incorrectly. FallbackMethod 'fallbackGetBookById' has launched");
        return BookDto.builder().build();
    }

    @HystrixCommand(groupKey = "bookService", fallbackMethod = "fallbackCreateBook", commandKey = "createBook")
    @Override
    public BookDto createBook(BookDto book) {
        Author author = authorService.getAuthorByName(book.getAuthor().getName());
        Style style = styleService.getStyleByName(book.getStyle().getName());
        return bookDtoConverter.mapToDto(bookRepository.save(Book.builder()
                .name(book.getName())
                .author(author)
                .style(style)
                .build()));
    }

    private BookDto fallbackCreateBook(BookDto book) {
        log.debug("Method createBook is working incorrectly. FallbackMethod 'fallbackCreateBook' has launched");
        return BookDto.builder().build();
    }

    @HystrixCommand(groupKey = "bookService", fallbackMethod = "fallbackUpdateBook", commandKey = "updateBook")
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
        bookRepository.save(bookEntity);
    }

    private void fallbackUpdateBook(BookDto book) {
        log.debug("Method updateBook is working incorrectly. FallbackMethod 'fallbackUpdateBook' has launched");
    }

    @HystrixCommand(groupKey = "bookService", fallbackMethod = "fallbackDeleteBookById", commandKey = "deleteBookById")
    @Override
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    private void fallbackDeleteBookById(long bookId) {
        log.debug("Method deleteBookById is working incorrectly. FallbackMethod 'fallbackDeleteBookById' has launched");
    }

}
