package ru.otus.homework6.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private int id;
    private String comment;
    private int bookId;
}
