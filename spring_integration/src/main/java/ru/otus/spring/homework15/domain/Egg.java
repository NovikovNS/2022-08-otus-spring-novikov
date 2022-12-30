package ru.otus.spring.homework15.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Egg {
    private final String dragonName;
    private final DragonType dragonType;
}
