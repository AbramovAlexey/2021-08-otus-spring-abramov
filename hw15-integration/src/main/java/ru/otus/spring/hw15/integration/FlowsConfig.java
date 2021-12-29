package ru.otus.spring.hw15.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import ru.otus.spring.hw15.domain.Vegetable;
import ru.otus.spring.hw15.service.Grower;
import ru.otus.spring.hw15.service.Harvester;
import ru.otus.spring.hw15.service.QualityController;
import ru.otus.spring.hw15.service.Sower;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FlowsConfig {

    private final Sower sower;
    private final Harvester harvester;
    private final Grower grower;
    private final QualityController qualityController;

    @Bean
    public IntegrationFlow sowFlow() {
        return IntegrationFlows.from("seedsChannel")
                               .log(LoggingHandler.Level.INFO, message -> "Received objects count: " + ((List)message.getPayload()).size())
                               .split()
                               .handle(sower, "sow")
                               .channel("sowedChannel")
                               .get();
    }

    @Bean
    public IntegrationFlow growFlow() {
        return IntegrationFlows.from("sowedChannel")
                               .handle(grower, "grow")
                               .channel("grownSeedsChannel")
                               .get();

    }

    @Bean
    public IntegrationFlow harvestFlow() {
        return IntegrationFlows.from("grownSeedsChannel")
                .handle(harvester, "harvest")
                .channel("qualityChannel")
                .get();
    }

    @Bean
    public IntegrationFlow qualityControlFlow() {
        return IntegrationFlows.from("qualityChannel")
                .routeToRecipients(routerSpec -> routerSpec.recipient("aggregateChannel", qualityController::passed)
                                                           .<Vegetable>recipient("qualityFailedNotificationChannel", v -> !qualityController.passed(v)))
                .get();
    }

    @Bean
    public IntegrationFlow aggregateFlow() {
        return IntegrationFlows.from("aggregateChannel")
                .aggregate()
                .channel("vegetablesChannel")
                .get();
    }

    @Bean
    public IntegrationFlow qualityFailedFlow() {
        return IntegrationFlows.from("qualityFailedNotificationChannel")
                .log(LoggingHandler.Level.WARN,  v -> "Quality control was failed for " + v.getPayload())
                .channel("aggregateChannel")
                .get();
    }

}
