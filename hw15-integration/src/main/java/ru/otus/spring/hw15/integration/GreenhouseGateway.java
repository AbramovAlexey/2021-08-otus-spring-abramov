package ru.otus.spring.hw15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.hw15.domain.Seed;
import ru.otus.spring.hw15.domain.Vegetable;

import java.util.Collection;

@MessagingGateway
public interface GreenhouseGateway {

    @Gateway(requestChannel = "seedsChannel", replyChannel = "vegetablesChannel")
    Collection<Vegetable> process(Collection<Seed> seeds);

}
