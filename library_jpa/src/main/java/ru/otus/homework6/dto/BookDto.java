package ru.otus.homework6.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
public class BookDto {
    private int id;
    private String name;
    private AuthorDto author;
    private StyleDto style;
    @ToString.Exclude
    private List<CommentDto> comments;
}
