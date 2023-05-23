package ru.otus.finalProject.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.finalProject.rest.dto.wishlist.CommentDto;
import ru.otus.finalProject.service.CommentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {
    private final CommentsService commentsService;

    @GetMapping("api/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByWishId(@RequestParam Long wishId) {
        return ResponseEntity.ok().body(commentsService.getCommentsByWishId(wishId));
    }

    @PostMapping("api/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsService.saveNewComment(commentDto));
    }
}
