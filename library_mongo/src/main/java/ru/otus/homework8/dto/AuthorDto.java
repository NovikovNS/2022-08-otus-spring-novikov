package ru.otus.homework8.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private long id;
    private String name;
}
