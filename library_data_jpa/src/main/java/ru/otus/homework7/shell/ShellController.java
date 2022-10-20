package ru.otus.homework7.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework7.domain.Book;
import ru.otus.homework7.service.BookService;
import ru.otus.homework7.service.CommentsService;
import ru.otus.homework7.service.IOService;
import ru.otus.homework7.service.MessageService;

@ShellComponent
public class ShellController {
    private final BookService bookService;
    private final IOService ioService;
    private final MessageService messageService;
    private final CommentsService commentsService;

    @Autowired
    public ShellController(BookService bookService, IOService ioService, MessageService messageService, CommentsService commentsService) {
        this.bookService = bookService;
        this.ioService = ioService;
        this.messageService = messageService;
        this.commentsService = commentsService;
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

    @ShellMethod(value = "New comment for book", key = {"new comment", "nc"})
    public void newComment() {
        ioService.outputString(messageService.getMessage("comments.enter_book_id"));
        var bookId = ioService.readInt();
        commentsService.saveNewComment(bookId);
    }

    @ShellMethod(value = "Update comment for book", key = {"update comment", "uc"})
    public void updateComment() {
        ioService.outputString(messageService.getMessage("comments.enter_comment_id"));
        var commentId = ioService.readInt();
        ioService.outputString(commentsService.getCommentById(commentId).toString());
        ioService.outputString(messageService.getMessage("updating_comment.enter_comment"));
        var newComment = ioService.readString();
        commentsService.updateComment(commentId, newComment);
        ioService.outputString(messageService.getMessage("creating_comment.success_creating"));
    }

    @ShellMethod(value = "Delete comment for book", key = {"delete comment", "dc"})
    public void deleteComment() {
        ioService.outputString(messageService.getMessage("comments.enter_comment_id"));
        var commentId = ioService.readInt();
        commentsService.deleteComment(commentId);
        ioService.outputString(messageService.getMessage("deleting_comment.success_deleting"));
    }

    @ShellMethod(value = "Read comments for book", key = {"read comments", "rc"})
    public void readComments() {
        ioService.outputString(messageService.getMessage("comments.enter_book_id"));
        var bookId = ioService.readInt();
        commentsService.getCommentsByBookId(bookId).forEach(comment -> ioService.outputString(comment.toString()));
    }
}
