package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao.BookRepository;
import ru.otus.homework7.domain.Author;
import ru.otus.homework7.domain.Style;
import ru.otus.homework7.domain.Book;

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
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book getBookById(long bookId) {
        return bookRepository.findBookById(bookId);
    }

    @Override
    @Transactional
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(long bookId) {
        Book book = bookRepository.findBookById(bookId);
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
        return bookRepository.save(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBookById(long bookId) {
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
