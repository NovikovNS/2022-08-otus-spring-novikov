package ru.otus.homework17.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatingBookDto {
    private String bookName;
    private String authorName;
    private String styleName;
}
