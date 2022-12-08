package ru.otus.homework11.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.dao.BookRepository;
import ru.otus.homework11.rest.dto.BookDto;
import ru.otus.homework11.rest.dto.converter.BookDtoConverter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BookController {

    private final BookRepository bookRepository;
    private final BookDtoConverter bookDtoConverter;

    @GetMapping("api/books")
    public Flux<BookDto> getAllBooks() {
        return bookRepository.findAll().map(bookDtoConverter::mapToDto);
    }

    @GetMapping("api/book/{bookId}")
    public Mono<ResponseEntity<BookDto>> getBook(@PathVariable String bookId) {
        return bookRepository.findById(bookId)
                .map(bookDtoConverter::mapToDto)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping("api/book")
    public Mono<BookDto> createBook (@RequestBody BookDto book) {
        return bookRepository.save(bookDtoConverter.mapToEntity(book))
                .map(bookDtoConverter::mapToDto);
    }

    @PutMapping("api/book/{bookId}")
    public Mono<ResponseEntity<BookDto>> editBook(@PathVariable("bookId") long id, @RequestBody BookDto book) {
        return bookRepository.save(bookDtoConverter.mapToEntity(book))
                .map(bookDtoConverter::mapToDto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("api/book/{bookId}")
    public Mono<ResponseEntity<Void>> deleteBook (@PathVariable("bookId") String bookId) {
        return bookRepository.deleteById(bookId)
                .map(ResponseEntity::ok);
    }
}
