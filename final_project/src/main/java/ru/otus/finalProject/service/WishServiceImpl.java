package ru.otus.finalProject.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.finalProject.dao.UserRepository;
import ru.otus.finalProject.dao.WishRepository;
import ru.otus.finalProject.domain.Availability;
import ru.otus.finalProject.domain.Need;
import ru.otus.finalProject.domain.Wish;
import ru.otus.finalProject.exception.WishNotFoundException;
import ru.otus.finalProject.rest.dto.converter.WishDtoConverter;
import ru.otus.finalProject.rest.dto.wishlist.WishDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishServiceImpl implements WishService {
    UserRepository userRepository;
    WishDtoConverter wishDtoConverter;
    WishRepository wishRepository;
    NeedService needService;
    AvailabilityService availabilityService;

    @Override
    public List<WishDto> getUserWishes(String userNickName) {
        return userRepository.findByNickname(userNickName)
                .orElseThrow(() -> new WishNotFoundException(String.format("Not found wish for userNickName:%s", userNickName)))
                .getWishes().stream().map(wishDtoConverter::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<WishDto> getOwnWishes() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new WishNotFoundException(String.format("Not found wish for userName:%s", user.getUsername())))
                .getWishes().stream().map(wishDtoConverter::mapToDto).collect(Collectors.toList());
        return null;
    }

    @Override
    public WishDto getWishById(long wishId) {
        return wishDtoConverter.mapToDto(wishRepository.findById(wishId)
                .orElseThrow(() -> new WishNotFoundException(String.format("Not found book with bookId:%s", wishId))));
    }

    @Override
    public WishDto createWish(WishDto wish) {
        return wishDtoConverter.mapToDto(wishRepository.save(wishDtoConverter.mapToEntity(wish)));
    }

    // TODO если поле не null в wish, то обновлять. Иначе - нет
    @Override
    @Transactional
    public void updateWish(WishDto wish) {
        Wish wishEntity = wishRepository.findById(wish.getId())
                .orElseThrow(() -> new WishNotFoundException(String.format("Not found wish with id %s", wish.getId())));

        Need need = needService.getNeedById(wish.getNeed().getId());
        Availability availability = availabilityService.getAvailabilityById(wish.getAvailability().getId());

        wishEntity.setDefinition(wish.getDefinition());
        wishEntity.setLink(wish.getLink());
        wishEntity.setPrice(wish.getPrice());
        wishEntity.setReason(wish.getReason());
        wishEntity.setNote(wish.getNote());
        wishEntity.setNeed(need);
        wishEntity.setAvailability(availability);
        wishRepository.save(wishEntity);
    }

    @Transactional
    @Override
    public void reserveWish(long wishId) {
        Wish wishEntity = wishRepository.findById(wishId)
                .orElseThrow(() -> new WishNotFoundException(String.format("Not found wish with id %s", wishId)));

        wishEntity.setReservation(true);
        wishRepository.save(wishEntity);
    }

    @Override
    public void deleteWishById(long wishId) {
        wishRepository.deleteById(wishId);
    }
}
