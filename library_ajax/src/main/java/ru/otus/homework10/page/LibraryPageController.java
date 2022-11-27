package ru.otus.homework10.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework10.rest.dto.BookDto;
import ru.otus.homework10.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibraryPageController {
    private final BookService bookService;

    @GetMapping("/library")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }
}
