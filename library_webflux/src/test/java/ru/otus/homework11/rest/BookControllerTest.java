package ru.otus.homework11.rest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.dao.BookRepository;
import ru.otus.homework11.domain.Author;
import ru.otus.homework11.domain.Book;
import ru.otus.homework11.domain.Style;
import ru.otus.homework11.rest.dto.AuthorDto;
import ru.otus.homework11.rest.dto.BookDto;
import ru.otus.homework11.rest.dto.StyleDto;
import ru.otus.homework11.rest.dto.converter.AuthorDtoConverter;
import ru.otus.homework11.rest.dto.converter.BookDtoConverter;
import ru.otus.homework11.rest.dto.converter.StyleDtoConverter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BookController.class)
@Import({BookDtoConverter.class, AuthorDtoConverter.class, StyleDtoConverter.class})
@TestPropertySource(properties = "mongock.enabled=false")
public class BookControllerTest {

    @MockBean
    BookRepository bookRepository;

    @Autowired
    private WebTestClient webClient;

    private static final Book EXPECTED_BOOK = Book.builder()
            .id("expectedBookId")
            .name("expectedName")
            .author(Author.builder()
                    .id("expectedAuthorId")
                    .name("expectedAuthorName")
                    .build())
            .style(Style.builder()
                    .id("expectedStyleId")
                    .name("expectedStyleName")
                    .build())
            .build();


    private static final BookDto EXPECTED_BOOK_DTO = BookDto.builder()
            .id(EXPECTED_BOOK.getId())
            .name(EXPECTED_BOOK.getName())
            .author(AuthorDto.builder()
                    .id(EXPECTED_BOOK.getAuthor().getId())
                    .name(EXPECTED_BOOK.getAuthor().getName())
                    .build())
            .style(StyleDto.builder()
                    .id(EXPECTED_BOOK.getStyle().getId())
                    .name(EXPECTED_BOOK.getStyle().getName())
                    .build())
            .build();

    private static final List<BookDto> EXPECTED_BOOKS = List.of(EXPECTED_BOOK_DTO);

    private static final Book CREATING_BOOK = Book.builder().id("creatingBookId").name("CreatingBook")
            .author(Author.builder().id("authorId1").name("CreatingAuthor").build())
            .style(Style.builder().id("styleId1").name("CreatingStyle").build())
            .build();

    private static final BookDto CREATING_BOOK_DTO = BookDto.builder().id("creatingBookId").name("CreatingBook")
            .author(AuthorDto.builder().id("authorId1").name("CreatingAuthor").build())
            .style(StyleDto.builder().id("styleId1").name("CreatingStyle").build())
            .build();

    @BeforeEach
    public void setUp() {
        when(bookRepository.findById(EXPECTED_BOOK.getId())).thenReturn(Mono.just(EXPECTED_BOOK));
        when(bookRepository.findAll()).thenReturn(Flux.just(EXPECTED_BOOK));
        when(bookRepository.save(CREATING_BOOK)).thenReturn(Mono.just(EXPECTED_BOOK));
        when(bookRepository.deleteById(EXPECTED_BOOK.getId())).thenReturn(Mono.empty());
    }

    @Test
    void shouldReturnAllBooks() {
        webClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .hasSize(EXPECTED_BOOKS.size())
                .value(value -> {
                    assertThat(value).usingRecursiveComparison().isEqualTo(List.of(EXPECTED_BOOK_DTO));
                });
    }

    @Test
    void shouldReturnBookById() {
        val bookId = "expectedBookId";
        webClient.get()
                .uri("/api/book/" + bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(value -> assertEquals(value, EXPECTED_BOOK_DTO));
    }

    @Test
    void shouldCreateBook() {
        webClient.post()
                .uri("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(CREATING_BOOK_DTO))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookDto.class)
                .value(value -> assertEquals(value, EXPECTED_BOOK_DTO));
    }

    @Test
    void shouldEditBook() throws Exception {
        val bookId = CREATING_BOOK.getId();
        webClient.put()
                .uri("/api/book/" + bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(CREATING_BOOK_DTO))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(value -> assertEquals(value, EXPECTED_BOOK_DTO));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        val bookId = EXPECTED_BOOK.getId();
        webClient.delete()
                .uri("/api/book/" + bookId)
                .exchange()
                .expectStatus().isOk();
    }

}
