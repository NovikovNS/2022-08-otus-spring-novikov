package ru.otus.spring.homework15.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Lizard {
    private String lizardName;
    private DragonType dragonType;
    private int size;
}
