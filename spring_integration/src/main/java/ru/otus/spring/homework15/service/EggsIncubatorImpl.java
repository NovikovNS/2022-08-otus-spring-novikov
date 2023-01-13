package ru.otus.spring.homework15.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.domain.DragonType;
import ru.otus.spring.homework15.domain.Egg;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EggsIncubatorImpl implements EggsIncubator {

    @Override
    public List<Egg> growDragonEggs() {
        return Arrays.stream(DragonType.values())
                .map(dragonType ->
                        Egg.builder()
                                .dragonName(String.format("%s", dragonType.getType()))
                                .dragonType(dragonType)
                                .build()).collect(Collectors.toList());
    }

}
