package ru.otus.homework17.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private long id;
    private String comment;
    private long bookId;
}
