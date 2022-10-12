package ru.otus.homework5.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private Long id;
    private String name;
}
