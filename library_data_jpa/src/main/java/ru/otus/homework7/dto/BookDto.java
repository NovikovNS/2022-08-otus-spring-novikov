package ru.otus.homework7.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
public class BookDto {
    private long id;
    private String name;
    private AuthorDto author;
    private StyleDto style;
    @ToString.Exclude
    private List<CommentDto> comments;
}
