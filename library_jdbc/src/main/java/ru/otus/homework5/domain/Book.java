package ru.otus.homework5.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private int id;
    private String name;
    private Author author;
    private Style style;
}
