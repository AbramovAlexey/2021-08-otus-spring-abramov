package ru.otus.spring.hw3.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "exam")
@Component
@Getter
@Setter
public class ExamConfig {

    private String csvFilename;
    private int minScore;

}
