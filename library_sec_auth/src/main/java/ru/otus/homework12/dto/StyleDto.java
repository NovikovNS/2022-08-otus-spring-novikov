package ru.otus.homework12.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StyleDto {
    private long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
