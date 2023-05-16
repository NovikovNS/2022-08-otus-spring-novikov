package ru.otus.finalProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.finalProject.rest.BookController;
import ru.otus.finalProject.rest.dto.AuthorDto;
import ru.otus.finalProject.rest.dto.BookDto;
import ru.otus.finalProject.rest.dto.StyleDto;
import ru.otus.finalProject.service.BookService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class Wish3ControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private BookService bookService;

    private static final List<BookDto> EXPECTED_BOOKS = List.of(BookDto.builder().build());
    private static final BookDto EXPECTED_BOOK = BookDto.builder().id(1L).build();
    private static final BookDto CREATING_BOOK = BookDto.builder().id(1L).name("CreatingBook")
            .author(AuthorDto.builder().id(1L).name("CreatingAuthor").build())
            .style(StyleDto.builder().id(1L).name("CreatingStyle").build())
            .build();

    @BeforeEach
    public void setUp() {
        when(bookService.getBookById(EXPECTED_BOOK.getId())).thenReturn(EXPECTED_BOOK);
        when(bookService.getAllBooks()).thenReturn(EXPECTED_BOOKS);
        when(bookService.createBook(CREATING_BOOK)).thenReturn(EXPECTED_BOOK);
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXPECTED_BOOKS)));
    }

    @Test
    void shouldReturnBookById() throws Exception {
        val bookId = 1L;
        mvc.perform(get("/api/book/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXPECTED_BOOK)));
    }

    @Test
    void shouldCreateBook() throws Exception {
        mvc.perform(post("/api/book")
                        .content(mapper.writeValueAsString(CREATING_BOOK))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(EXPECTED_BOOK)));

        verify(bookService).createBook(CREATING_BOOK);
    }

    @Test
    void shouldEditBook() throws Exception {
        val bookId = 1L;
        mvc.perform(put("/api/book/" + bookId)
                        .content(mapper.writeValueAsString(CREATING_BOOK))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService).updateBook(CREATING_BOOK);
    }

    @Test
    void shouldDeleteBook() throws Exception {
        long bookId = EXPECTED_BOOK.getId();
        mvc.perform(delete("/api/book/" + bookId))
                .andExpect(status().isOk());
        verify(bookService).deleteBookById(bookId);
    }
}
