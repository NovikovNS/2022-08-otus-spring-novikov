package ru.otus.spring.homework15.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.domain.Egg;
import ru.otus.spring.homework15.domain.Lizard;

@Service
public class EggsServiceImpl implements EggsService {
    @Override
    public Lizard hatch(Egg egg) {
        System.out.println("Egg with name " + egg.getDragonName() + " is hatching");
        int lizardSize = RandomUtils.nextInt(1, 10);
        return Lizard.builder()
                .dragonType(egg.getDragonType())
                .lizardName(egg.getDragonName())
                .size(lizardSize)
                .build();
    }
}
