package ru.otus.homework11.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LibraryPageController {

    @GetMapping("/library")
    public String getAllBooks(Model model) {
        return "listBooks";
    }
}
