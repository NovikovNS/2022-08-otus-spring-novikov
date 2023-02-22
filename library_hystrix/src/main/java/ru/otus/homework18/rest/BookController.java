package ru.otus.homework18.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework18.rest.dto.BookDto;
import ru.otus.homework18.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BookController {
    private final BookService bookService;

    @GetMapping("api/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("api/book/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable long bookId) {
        return ResponseEntity.ok().body(bookService.getBookById(bookId));
    }

    @PostMapping("api/book")
    public ResponseEntity<BookDto> createBook (@RequestBody BookDto book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
    }

    @PutMapping("api/book/{bookId}")
    public ResponseEntity<?> editBook(@PathVariable("bookId") long id, @RequestBody BookDto book) {
        bookService.updateBook(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("api/book/{bookId}")
    public ResponseEntity<?> deleteBook (@PathVariable("bookId")long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok().build();
    }
}
