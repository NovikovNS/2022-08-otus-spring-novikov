package ru.otus.homework9.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StyleDto {
    private long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
