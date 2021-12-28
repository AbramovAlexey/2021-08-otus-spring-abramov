package ru.otus.spring.hw15.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class ChannelsConfig {

    @Bean
    public QueueChannel seedsChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public QueueChannel sowedSeedsChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public QueueChannel growedSeedsChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public QueueChannel vegetablesChannel() {
        return MessageChannels.queue().get();
    }

}
