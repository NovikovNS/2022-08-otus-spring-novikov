package ru.otus.finalProject.service;

import ru.otus.finalProject.rest.dto.wishlist.WishDto;

import java.util.List;

public interface WishService {
    List<WishDto> getUserWishes(String userNickName);
    List<WishDto> getOwnWishes();
    WishDto getWishById(long wishId);
    WishDto createWish(WishDto wish);
    void updateWish(WishDto wish);
    void deleteWishById(long wishId);
    void reserveWish(long wishId);
}
