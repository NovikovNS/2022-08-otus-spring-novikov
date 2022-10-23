package ru.otus.homework6.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private int id;
    private String name;
}
