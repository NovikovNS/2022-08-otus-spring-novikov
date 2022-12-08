package ru.otus.homework11.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private String id;
    private String comment;
    private long bookId;
}
