package ru.otus.spring.hw14.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.hw14.repository.AuthorRepository;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    private static final int CHUNK_SIZE = 2;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private AuthorRepository authorRepository;

}
