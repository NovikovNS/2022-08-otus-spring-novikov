package ru.otus.finalProject.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.finalProject.rest.dto.wishlist.CommentDto;
import ru.otus.finalProject.service.CommentsService;

import java.util.List;

@Controller
@AllArgsConstructor
public class CommentPageController {
    private final CommentsService commentsService;

    @RequestMapping("/wishlist/{userNickName}/wishes/{wishId}/comments")
    public String getCommentsByWishId(
            @PathVariable Long wishId,
            @PathVariable String userNickName,
            Model model) {
        List<CommentDto> comments = commentsService.getCommentsByWishId(wishId);
        model.addAttribute("comments", comments);
        model.addAttribute("userNickName", userNickName);
        model.addAttribute("wishId", wishId);
        return "comments";
    }
}
