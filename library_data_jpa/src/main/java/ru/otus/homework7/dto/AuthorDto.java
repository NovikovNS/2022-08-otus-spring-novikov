package ru.otus.homework7.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private long id;
    private String name;
}
