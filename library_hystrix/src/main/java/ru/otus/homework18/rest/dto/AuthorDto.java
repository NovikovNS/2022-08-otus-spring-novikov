package ru.otus.homework18.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
