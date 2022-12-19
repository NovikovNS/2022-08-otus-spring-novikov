package ru.otus.homework14.service;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.homework14.dao.BookRepository;
import ru.otus.homework14.dao.CommentRepository;
import ru.otus.homework14.domain.Author;
import ru.otus.homework14.domain.Book;
import ru.otus.homework14.domain.Comment;
import ru.otus.homework14.domain.Style;
import ru.otus.homework14.dto.AuthorDto;
import ru.otus.homework14.dto.BookDto;
import ru.otus.homework14.dto.CommentDto;
import ru.otus.homework14.dto.StyleDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceImplTest {

    @Configuration
    @Import(BookServiceImpl.class)
    @ComponentScan({"ru.otus.homework14.dto"})
    static class BookServiceImplConfiguration {
    }

    private static final Author TEST_AUTHOR = Author.builder().id("1").name("TestAuthor").build();
    private static final AuthorDto TEST_AUTHOR_DTO = AuthorDto.builder().id(TEST_AUTHOR.getId())
            .name(TEST_AUTHOR.getName()).build();
    private static final Style TEST_STYLE = Style.builder().id("1").name("TestStyle").build();
    private static final StyleDto TEST_STYLE_DTO = StyleDto.builder().id(TEST_STYLE.getId())
            .name(TEST_STYLE.getName()).build();
    private static final Comment TEST_COMMENT = Comment.builder().id("1").comment("TestComment").build();
    private static final CommentDto TEST_COMMENT_DTO = CommentDto.builder().id(TEST_COMMENT.getId())
            .comment(TEST_COMMENT.getComment()).build();
    private static final Book TEST_BOOK = Book.builder().id("1").name("TestBook").author(TEST_AUTHOR)
            .style(TEST_STYLE).comments(List.of(TEST_COMMENT)).build();
    private static final BookDto TEST_BOOK_DTO = BookDto.builder().id(TEST_BOOK.getId())
            .name(TEST_BOOK.getName()).author(TEST_AUTHOR_DTO).style(TEST_STYLE_DTO).build();

    @Autowired
    BookService bookService;

    @MockBean
    AuthorService authorService;

    @MockBean
    StyleService styleService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        when(bookRepository.findById(TEST_BOOK.getId())).thenReturn(Optional.of(TEST_BOOK));
        when(bookRepository.findAll()).thenReturn(List.of(TEST_BOOK));
    }

    @Test
    void shouldFindBookById() {
        BookDto expectedBook = bookService.getBookById(TEST_BOOK.getId());
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(TEST_BOOK_DTO);
    }

    @Test
    void shouldFindAllBooks() {
        List<BookDto> booksList = bookService.getAllBooks();
        assertEquals(1, booksList.size());
        assertThat(booksList)
                .usingRecursiveFieldByFieldElementComparator(RecursiveComparisonConfiguration.builder().build())
                .containsExactlyInAnyOrderElementsOf(List.of(TEST_BOOK_DTO));
    }
}
