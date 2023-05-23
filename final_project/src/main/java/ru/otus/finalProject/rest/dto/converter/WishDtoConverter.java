package ru.otus.finalProject.rest.dto.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.domain.Availability;
import ru.otus.finalProject.domain.Need;
import ru.otus.finalProject.domain.Wish;
import ru.otus.finalProject.rest.dto.wishlist.AvailabilityDto;
import ru.otus.finalProject.rest.dto.wishlist.NeedDto;
import ru.otus.finalProject.rest.dto.wishlist.WishDto;

@Service
@AllArgsConstructor
public class WishDtoConverter implements DtoConverter<Wish, WishDto>{
    private final DtoConverter<Availability, AvailabilityDto> availabilityConverter;
    private final DtoConverter<Need, NeedDto> needConverter;

    @Override
    public WishDto mapToDto(Wish entity) {
        return WishDto.builder()
                .id(entity.getId())
                .definition(entity.getDefinition())
                .link(entity.getLink())
                .price(entity.getPrice())
                .reason(entity.getReason())
                .note(entity.getNote())
                .reservation(entity.getReservation())
                .need(needConverter.mapToDto(entity.getNeed()))
                .availability(availabilityConverter.mapToDto(entity.getAvailability()))
                .comments(entity.getComments())
                .build();
    }

    @Override
    public Wish mapToEntity(WishDto dto) {
        return Wish.builder()
                .definition(dto.getDefinition())
                .link(dto.getLink())
                .price(dto.getPrice())
                .reason(dto.getReason())
                .note(dto.getNote())
                .reservation(dto.getReservation())
                .userId(dto.getUserId())
                .need(needConverter.mapToEntity(dto.getNeed()))
                .availability(availabilityConverter.mapToEntity(dto.getAvailability()))
                .comments(null)
                .build();
    }
}
