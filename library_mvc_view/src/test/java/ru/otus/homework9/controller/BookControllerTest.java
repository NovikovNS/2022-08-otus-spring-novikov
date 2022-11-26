package ru.otus.homework9.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework9.service.BookService;
import ru.otus.homework9.service.CommentsService;
import ru.otus.homework9.service.IOService;
import ru.otus.homework9.service.MessageService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private IOService ioService;
    @MockBean
    private MessageService messageService;
    @MockBean
    private CommentsService commentsService;


    @Test
    void shouldReturnAllBooks() throws Exception {
        mvc.perform(get("/books")).andExpect(status().isOk());
    }

}
