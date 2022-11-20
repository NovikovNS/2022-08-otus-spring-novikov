package ru.otus.homework9.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework9.dto.AuthorDto;
import ru.otus.homework9.dto.BookDto;
import ru.otus.homework9.dto.CommentDto;
import ru.otus.homework9.dto.StyleDto;
import ru.otus.homework9.service.BookService;
import ru.otus.homework9.service.CommentsService;
import ru.otus.homework9.service.IOService;
import ru.otus.homework9.service.MessageService;

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
        ioService.outputString(messageService.getMessage("creating_book.enter_book_name"));
        String bookName = ioService.readString();
        ioService.outputString(messageService.getMessage("creating_book.enter_author_name"));
        String authorName = ioService.readString();
        ioService.outputString(messageService.getMessage("creating_book.enter_style_name"));
        String styleName = ioService.readString();
        BookDto book = BookDto.builder()
                .name(bookName)
                .author(AuthorDto.builder().name(authorName).build())
                .style(StyleDto.builder().name(styleName).build())
                .build();

        bookService.createBook(book);
        ioService.outputString(messageService.getMessage("creating_book.success_creating"));
    }

    @ShellMethod(value = "Update book", key = {"update book", "ub"})
    public void updateBook() {
        ioService.outputString(messageService.getMessage("updating_book.enter_book_id"));
        var bookId = ioService.readInt();
        ioService.outputString(messageService.getMessage("updating_book.enter_book_name"));
        String bookName = ioService.readString();
        ioService.outputString(messageService.getMessage("updating_book.enter_author_name"));
        String authorName = ioService.readString();
        ioService.outputString(messageService.getMessage("updating_book.enter_style_name"));
        String styleName = ioService.readString();
        BookDto book = BookDto.builder()
                .id(bookId)
                .name(bookName)
                .author(AuthorDto.builder().name(authorName).build())
                .style(StyleDto.builder().name(styleName).build())
                .build();

        bookService.updateBook(book);
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
        ioService.outputString(messageService.getMessage("creating_comment.enter_comment"));
        String commentText = ioService.readString();
        CommentDto newComment = CommentDto.builder().bookId(bookId).comment(commentText).build();
        commentsService.saveNewComment(newComment);
    }

    @ShellMethod(value = "Update comment for book", key = {"update comment", "uc"})
    public void updateComment() {
        ioService.outputString(messageService.getMessage("comments.enter_comment_id"));
        var commentId = ioService.readInt();
        var commentForUpdating = commentsService.getCommentById(commentId);
        ioService.outputString(commentForUpdating.toString());
        ioService.outputString(messageService.getMessage("updating_comment.enter_comment"));
        var newComment = ioService.readString();
        CommentDto updatingComment = CommentDto.builder()
                .id(commentId)
                .comment(newComment)
                .bookId(commentForUpdating.getBookId()).build();
        commentsService.updateComment(updatingComment);
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
