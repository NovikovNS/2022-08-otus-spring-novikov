package ru.otus.homework10.rest.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("api/books")
    List<BookDto> getAllBooks(Model model) {
        return bookService.getAllBooks();
    }

    @GetMapping("api/book/{bookId}")
    BookDto getBook(@PathVariable long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("api/book")
    BookDto createBook (@RequestParam BookDto book) {
        return bookService.createBook(book);
    }

    @PutMapping("api/book")
    void editBook(@RequestParam BookDto book) {
        bookService.updateBook(book);
    }

    @DeleteMapping("api/book/{bookId}")
    void deleteBook (@PathVariable("bookId")long bookId) {
        bookService.deleteBookById(bookId);
    }
}
