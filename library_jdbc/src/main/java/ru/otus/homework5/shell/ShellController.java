package ru.otus.homework5.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.service.BookService;
import ru.otus.homework5.service.IOService;
import ru.otus.homework5.service.MessageService;

@ShellComponent
public class ShellController {
    private final BookService bookService;
    private final IOService ioService;
    private final MessageService messageService;

    @Autowired
    public ShellController(BookService bookService, IOService ioService, MessageService messageService) {
        this.bookService = bookService;
        this.ioService = ioService;
        this.messageService = messageService;
    }

    @ShellMethod(value = "Get all books from library", key = {"get all books", "ga"})
    public void getAllBooks(){
        bookService.getAllBooks().forEach(book -> ioService.outputString(book.toString()));
    }

    @ShellMethod(value = "Get book by id", key = {"get book", "gb"})
    public void getBookById() {
        ioService.outputString(messageService.getMessage("getting_book.enter_book_id"));
        var bookId = ioService.readInt();
        ioService.outputString(bookService.getBookById(bookId).toString());
    }

    @ShellMethod(value = "create book", key = {"create book", "cb"})
    public void createBook() {
        Book book = bookService.createBook();
        bookService.saveNewBook(book);
    }

    @ShellMethod(value = "Update book", key = {"update book", "ub"})
    public void updateBook() {
        ioService.outputString(messageService.getMessage("updating_book.enter_book_id"));
        var bookId = ioService.readInt();
        bookService.updateBook(bookId);
    }

    @ShellMethod(value = "Delete book", key = {"del book", "db"})
    public void deleteBook() {
        ioService.outputString(messageService.getMessage("deleting_book.enter_book_id"));
        var bookId = ioService.readInt();
        bookService.deleteBookById(bookId);
        ioService.outputString(messageService.getMessage("deleting_book.succeed_deleted"));
    }
}
