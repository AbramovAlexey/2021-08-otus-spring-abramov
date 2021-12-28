package ru.otus.spring.hw15.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import ru.otus.spring.hw15.service.Grower;
import ru.otus.spring.hw15.service.Harvester;
import ru.otus.spring.hw15.service.Sower;

@Configuration
@RequiredArgsConstructor
public class FlowsConfig {

    private final Sower sower;
    private final Harvester harvester;
    private final Grower grower;

    @Bean
    public IntegrationFlow sowFlow() {
        return IntegrationFlows.from("seedsChannel")
                               .split()
                               .handle(sower, "sow")
                               .channel("sowedChannel")
                               .get();
    }

    @Bean
    public IntegrationFlow growFlow() {
        return IntegrationFlows.from("sowedChannel")
                               .handle(grower, "grow")
                               .aggregate()
                               .channel("vegetablesChannel")
                               .get();

    }

}
