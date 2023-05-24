package ru.otus.finalProject.rest.dto.wishlist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private long id;
    private String comment;
    private long wishId;
}
