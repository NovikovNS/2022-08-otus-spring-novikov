package ru.otus.finalProject.rest.dto.wishlist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NeedDto {
    private long id;
    private String definition;

    @Override
    public String toString() {
        return definition;
    }
}
