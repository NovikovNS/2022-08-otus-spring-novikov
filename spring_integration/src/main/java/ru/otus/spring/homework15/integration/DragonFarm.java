package ru.otus.spring.homework15.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework15.domain.Dragon;
import ru.otus.spring.homework15.domain.Egg;

import java.util.List;

@MessagingGateway
public interface DragonFarm {

    @Gateway(requestChannel = "eggsChannel", replyChannel = "dragonChannel")
    List<Dragon> process(List<Egg> eggs);
}
