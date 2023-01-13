package ru.otus.spring.homework15.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.domain.Dragon;
import ru.otus.spring.homework15.domain.Lizard;

@Service
public class LizardServiceImpl implements LizardService {

    @Override
    public Dragon upgrade(Lizard lizard) {
        System.out.println("Lizard with name " + lizard.getLizardName() + " is upgrading");
        int dragonSize = lizard.getSize() + RandomUtils.nextInt(0, 5);
        return Dragon.builder()
                .dragonName(lizard.getLizardName())
                .dragonType(lizard.getDragonType())
                .size(dragonSize)
                .build();
    }
}
