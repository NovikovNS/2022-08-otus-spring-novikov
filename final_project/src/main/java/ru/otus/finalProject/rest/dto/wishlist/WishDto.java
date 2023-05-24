package ru.otus.finalProject.rest.dto.wishlist;

import lombok.Builder;
import lombok.Data;
import ru.otus.finalProject.domain.Comment;

import java.util.List;

@Builder
@Data
public class WishDto {
    private Long id;
    private String definition;
    private String link;
    private String price;
    private String reason;
    private String note;
    private Boolean reservation;
    private long userId;
    private NeedDto need;
    private AvailabilityDto availability;
    private List<Comment> comments;
}
