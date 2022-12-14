package ru.otus.homework5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework5.dao.AuthorRepository;
import ru.otus.homework5.dao.BookRepository;
import ru.otus.homework5.dao.StyleRepository;
import ru.otus.homework5.domain.Author;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.domain.Style;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final StyleService styleService;
    private final IOService ioService;
    private final MessageService messageService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, StyleRepository styleRepository, AuthorService authorService, StyleService styleService, IOService ioService, MessageService messageService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.styleService = styleService;
        this.ioService = ioService;
        this.messageService = messageService;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    @Override
    public Book getBookById(int bookId) {
        return bookRepository.getBookById(bookId);
    }

    @Override
    public long saveNewBook(Book book) {
        return bookRepository.saveNewBook(book);
    }

    @Override
    public void updateBook(int bookId) {
        Book book = bookRepository.getBookById(bookId);
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
    public void deleteBookById(int bookId) {
        bookRepository.deleteBookById(bookId);
    }

    @Override
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
