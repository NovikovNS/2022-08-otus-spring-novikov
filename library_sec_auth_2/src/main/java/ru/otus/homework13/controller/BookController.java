package ru.otus.homework13.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework13.dto.AuthorDto;
import ru.otus.homework13.dto.BookDto;
import ru.otus.homework13.dto.StyleDto;
import ru.otus.homework13.service.AuthorService;
import ru.otus.homework13.service.BookService;
import ru.otus.homework13.service.StyleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final StyleService styleService;
    private final AuthorService authorService;

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/books/create")
    public String createBookPage(Model model) {
        BookDto book = BookDto.builder().id(null).name("").build();
        predictModelForCreatingAndUpdatingBook(model, book);
        return "edit_create_book";
    }

    @PostMapping("/books/create")
    public String createBook (@ModelAttribute("book") BookDto book) {
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String editBookPage(@RequestParam("id") long id, Model model) {
        BookDto book = bookService.getBookById(id);
        predictModelForCreatingAndUpdatingBook(model, book);
        return "edit_create_book";
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

    private void predictModelForCreatingAndUpdatingBook(Model model, BookDto book) {
        List<StyleDto> styles = styleService.getAllStyles();
        List<AuthorDto> authors = authorService.getAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("styles", styles);
        model.addAttribute("authors", authors);
    }
}
