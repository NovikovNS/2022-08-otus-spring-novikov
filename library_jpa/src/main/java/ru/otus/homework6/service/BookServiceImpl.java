package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao.BookRepository;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Style;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final StyleService styleService;
    private final IOService ioService;
    private final MessageService messageService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           StyleService styleService,
                           IOService ioService,
                           MessageService messageService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.styleService = styleService;
        this.ioService = ioService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    @Override
    @Transactional
    public Book getBookById(int bookId) {
        return bookRepository.getBookById(bookId).orElseThrow();
    }

    @Override
    @Transactional
    public long saveNewBook(Book book) {
        return bookRepository.saveNewBook(book);
    }

    @Override
    @Transactional
    public void updateBook(int bookId) {
        Book book = bookRepository.getBookById(bookId).get();
        ioService.outputString(messageService.getMessageWithArgs("updating_book.book_finded", new String[]{book.toString()}));
        ioService.outputString(messageService.getMessage("updating_book.enter_book_name"));
        String bookName = ioService.readString();
        ioService.outputString(messageService.getMessage("updating_book.enter_author_name"));
        String authorName = ioService.readString();
        ioService.outputString(messageService.getMessage("updating_book.enter_style_name"));
        String styleName = ioService.readString();

        Author author = authorService.getAuthorByName(authorName);
        Style style = styleService.getStyleByName(styleName);

        Book updatedBook = Book.builder()
                .id(bookId)
                .name(bookName)
                .author(author)
                .style(style)
                .build();
        bookRepository.updateBook(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBookById(int bookId) {
        bookRepository.deleteBookById(bookId);
    }

    @Override
    @Transactional
    public Book createBook() {
        ioService.outputString(messageService.getMessage("creating_book.enter_book_name"));
        String bookName = ioService.readString();
        ioService.outputString(messageService.getMessage("creating_book.enter_author_name"));
        String authorName = ioService.readString();
        ioService.outputString(messageService.getMessage("creating_book.enter_style_name"));
        String styleName = ioService.readString();

        Author author = authorService.getAuthorByName(authorName);
        Style style = styleService.getStyleByName(styleName);

        return Book.builder()
                .name(bookName)
                .author(author)
                .style(style)
                .build();
    }

}
