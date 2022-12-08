package ru.otus.homework11.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StyleDto {
    private String id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
