package ru.otus.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework10.dto.BookDto;
import ru.otus.homework10.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/create")
    public String createBookPage(Model model) {
        BookDto book = BookDto.builder().name("").build();
        model.addAttribute("book", book);
        return "createBook";
    }

    @PostMapping("/books/create")
    public String createBook (@ModelAttribute("book") BookDto book) {
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") long id, Model model) {
        BookDto book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/books/edit")
    public String editBook (@ModelAttribute("book") BookDto book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @PostMapping("/books/delete")
    public String deleteBook (@RequestParam("id")long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/books";
    }
}
