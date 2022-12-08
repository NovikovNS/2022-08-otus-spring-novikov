package ru.otus.homework11.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private AuthorDto author;
    private StyleDto style;
    @ToString.Exclude
    private List<CommentDto> comments;
}
