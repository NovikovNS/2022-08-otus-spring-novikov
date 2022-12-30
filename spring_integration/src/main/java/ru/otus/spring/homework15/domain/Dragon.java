package ru.otus.spring.homework15.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Dragon {
    private String dragonName;
    private final DragonType dragonType;
    private int size;
}
