package ru.otus.homework10.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private long id;
    private String comment;
    private long bookId;
}
