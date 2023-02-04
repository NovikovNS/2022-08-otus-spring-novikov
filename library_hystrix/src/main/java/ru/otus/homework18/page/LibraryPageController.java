package ru.otus.homework18.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.homework18.service.BookService;

@Controller
@RequiredArgsConstructor
public class LibraryPageController {
    private final BookService bookService;

    @GetMapping("/library")
    public String getAllBooks(Model model) {
        return "listBooks";
    }
}
