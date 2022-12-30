package ru.otus.spring.homework15.service;

import ru.otus.spring.homework15.domain.Dragon;
import ru.otus.spring.homework15.domain.Lizard;

public interface LizardService {
    Dragon upgrade(Lizard lizard);
}
