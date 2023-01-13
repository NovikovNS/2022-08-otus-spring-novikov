package ru.otus.spring.homework15.integration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.homework15.domain.Dragon;
import ru.otus.spring.homework15.service.EggsService;
import ru.otus.spring.homework15.service.LizardService;

@Configuration
@AllArgsConstructor
public class DragonIntegrationConfig {

    private final EggsService eggsService;
    private final LizardService lizardService;

    @Bean
    public QueueChannel eggsChannel() {
        return MessageChannels.queue( 10 ).get();
    }

    @Bean
    public PublishSubscribeChannel lizardChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel notReadyDragonChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @ServiceActivator(inputChannel = "notReadyDragonChannel")
    public void dragonNotReady (Dragon dragon) {
        System.out.println(dragon.getDragonName() + " is not ready to fights, send it to farm " + "size is only " + dragon.getSize());
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 2 ).get();
    }

    @Bean
    public IntegrationFlow fromEggToLizard() {
        return IntegrationFlows.from(eggsChannel())
                .split()
                .handle(eggsService, "hatch")
                .channel("lizardChannel")
                .get();
    }

    @Bean
    public IntegrationFlow fromLizardToDragon() {
        return IntegrationFlows.from(lizardChannel())
                .split()
                .handle(lizardService, "upgrade")
                .<Dragon>filter(readyDragon -> readyDragon.getSize() > 6,
                        notReadyDragon -> notReadyDragon.discardChannel(notReadyDragonChannel()))
                .channel("dragonChannel")
                .get();
    }
}
