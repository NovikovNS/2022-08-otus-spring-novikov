package ru.otus.finalProject.rest.dto.wishlist;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AvailabilityDto {
    private long id;
    private String definition;

    @Override
    public String toString() {
        return definition;
    }
}
