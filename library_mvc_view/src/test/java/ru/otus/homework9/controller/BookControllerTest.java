package ru.otus.homework9.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework9.dto.AuthorDto;
import ru.otus.homework9.dto.BookDto;
import ru.otus.homework9.dto.StyleDto;
import ru.otus.homework9.service.AuthorService;
import ru.otus.homework9.service.BookService;
import ru.otus.homework9.service.StyleService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private StyleService styleService;
    @MockBean
    private AuthorService authorService;

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
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", EXPECTED_BOOKS))
                .andExpect(view().name("listBooks"));
    }

    @Test
    void shouldReturnViewEditBook() throws Exception {
        mvc.perform(get("/edit")
                        .queryParam("id", Long.toString(EXPECTED_BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("edit_create_book"));
    }

    @Test
    void shouldReturnViewCreateBook() throws Exception {
        mvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("edit_create_book"));
    }

    @Test
    void shouldCreateBook() throws Exception {
        mvc.perform(post("/books/create")
                        .flashAttr("book", CREATING_BOOK))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(redirectedUrl("/books"));

        verify(bookService).createBook(CREATING_BOOK);
    }

    @Test
    void shouldEditBook() throws Exception {
        mvc.perform(post("/books/edit")
                        .flashAttr("book", CREATING_BOOK))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(redirectedUrl("/books"));

        verify(bookService).updateBook(CREATING_BOOK);
    }

    @Test
    void shouldDeleteBook() throws Exception {
        long bookId = EXPECTED_BOOK.getId();
        mvc.perform(post("/books/delete")
                .queryParam("id", Long.toString(bookId)))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(redirectedUrl("/books"));
        verify(bookService).deleteBookById(bookId);
    }
}
