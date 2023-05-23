package ru.otus.finalProject.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.finalProject.rest.dto.wishlist.WishDto;
import ru.otus.finalProject.service.WishService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WishListPageController {
    private final WishService wishService;

    @GetMapping("/wishlist/{usernickname}")
    public String showWishListForm(@PathVariable("usernickname")String userNickname, Model model) {
        List<WishDto> wishes = wishService.getUserWishes(userNickname);
        model.addAttribute("wishes", wishes);
        model.addAttribute("userNickname", userNickname);
        return "wishlist";
    }

    @GetMapping("/mywishlist")
    public String getMyWishes(Model model) {
        List<WishDto> wishes = wishService.getOwnWishes();
        model.addAttribute("wishes", wishes);
        return "mywishlist";
    }
}
