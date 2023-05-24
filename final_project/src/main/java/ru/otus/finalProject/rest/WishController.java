package ru.otus.finalProject.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.finalProject.rest.dto.wishlist.WishDto;
import ru.otus.finalProject.service.WishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class WishController {

    @Autowired
    private final WishService wishService;

    @GetMapping("api/wishes")
    public ResponseEntity<List<WishDto>> getAllWishes(@RequestParam String userNickName) {
        return ResponseEntity.ok().body(wishService.getUserWishes(userNickName));
    }

    @GetMapping("api/wish/{wishId}")
    public ResponseEntity<WishDto> getWish(@PathVariable long wishId) {
        return ResponseEntity.ok().body(wishService.getWishById(wishId));
    }

    @PostMapping("api/wish")
    public ResponseEntity<WishDto> createWish (@RequestBody WishDto wish) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishService.createWish(wish));
    }

    @PutMapping("api/wish/{wishId}")
    public ResponseEntity<?> editBook(@PathVariable("wishId") long wishId, @RequestBody WishDto wish) {
        wishService.updateWish(wish);
        return ResponseEntity.ok().build();
    }

    @PutMapping("api/reservedWish/{wishId}")
    public ResponseEntity<?> reserveBook(@PathVariable("wishId") long wishId, @RequestBody WishDto wish) {
        wishService.reserveWish(wishId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("api/wish/{wishId}")
    public ResponseEntity<?> deleteBook (@PathVariable("wishId")long wishId) {
        wishService.deleteWishById(wishId);
        return ResponseEntity.ok().build();
    }


}
