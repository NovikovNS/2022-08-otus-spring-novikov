package ru.otus.homework9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework9.dto.BookDto;
import ru.otus.homework9.service.BookService;
import ru.otus.homework9.service.CommentsService;
import ru.otus.homework9.service.IOService;
import ru.otus.homework9.service.MessageService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final IOService ioService;
    private final MessageService messageService;
    private final CommentsService commentsService;

    @Autowired
    public BookController(BookService bookService, IOService ioService, MessageService messageService, CommentsService commentsService) {
        this.bookService = bookService;
        this.ioService = ioService;
        this.messageService = messageService;
        this.commentsService = commentsService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") long id, Model model) {
        BookDto book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/books/edit")
    public String editBook (BookDto book, Model model) {
        bookService.updateBook(book);
        return "redirect:/books";
    }
}
