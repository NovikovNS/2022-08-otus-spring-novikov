package ru.otus.spring.homework15.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.domain.Dragon;
import ru.otus.spring.homework15.domain.Egg;
import ru.otus.spring.homework15.integration.DragonFarm;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DragonServiceImpl implements DragonService {
    private final EggsIncubator eggsIncubator;
    private final DragonFarm dragonFarm;

    public void raiseDragons() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        while ( true ) {

            pool.execute( () -> {
                List<Egg> dragonEggs = eggsIncubator.growDragonEggs();
                System.out.println("These dragon eggs are ready to hatch: " +
                        dragonEggs.stream().map(Egg::getDragonName).collect(Collectors.joining(",")));
                List<Dragon> dragons = dragonFarm.process(dragonEggs);
                System.out.println("These dragons are ready to fights: " +
                        dragons.stream().map(Dragon::getDragonName).collect(Collectors.joining(",")));
            });

            try {
                Thread.sleep( 7000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
