package ru.otus.spring.homework15.service;


import ru.otus.spring.homework15.domain.Egg;
import ru.otus.spring.homework15.domain.Lizard;

public interface EggsService {
    Lizard hatch(Egg egg);
}
