package ru.otus.spring.homework15.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DragonType {
    CHINESE_BALL("Китайский шар"),
    HUNGARIAN_TAILING("Венгерский хвосторог"),
    VALIAN_GREEN("Валийский зелёный"),
    PERUVIAN_SERPENT("Перуанский змеезуб"),
    SWEDISH_SHORTSNOUT("Шведский короткорылый");

    private final String type;
}
