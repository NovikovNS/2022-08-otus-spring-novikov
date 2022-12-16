package ru.otus.homework11.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    public BookDto setId(String id) {
        this.id = id;
        return this;
    }

    private String id;
    private String name;
    private AuthorDto author;
    private StyleDto style;
}
