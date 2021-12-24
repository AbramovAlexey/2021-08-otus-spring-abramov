package ru.otus.spring.hw14.job;

import com.mongodb.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.hw14.job.processors.BookItemProcessor;
import ru.otus.spring.hw14.job.readers.ItemReaderCreator;
import ru.otus.spring.hw14.job.writers.AuthorItemWriter;
import ru.otus.spring.hw14.job.writers.GenreItemWriter;
import ru.otus.spring.hw14.model.*;
import ru.otus.spring.hw14.repository.*;

@Configuration
@EnableCaching
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfig {

    private static final int CHUNK_SIZE = 2;
    public static final String JOB_NAME = "copyLibrary";

    private final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final BookRepositoryMongo bookRepositoryMongo;
    private final ItemReaderCreator itemReaderCreator;
    private final AuthorItemWriter authorItemWriter;
    private final GenreItemWriter genreItemWriter;
    private final BookItemProcessor bookItemProcessor;
    private final MongoTemplate mongoTemplate;
    private final IdRelationRepository idRelationRepository;

    @Bean
    public Job importBookJob(Step copyAuthorStep, Step copyGenreStep, JobExecutionListener jobExecutionListener,
                             Step copyBookStep) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(copyAuthorStep)
                .next(copyGenreStep)
                .next(copyBookStep)
                .end()
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step copyAuthorStep(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("copyAuthor")
                .<Author, AuthorMongo>chunk(CHUNK_SIZE)
                .reader(itemReaderCreator.getReaderByRepository(authorRepository))
                .processor((ItemProcessor<Author, AuthorMongo>)item -> new AuthorMongo(String.valueOf(item.getId()), item.getFullName()))
                .writer(authorItemWriter)
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    public Step copyGenreStep(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("copyGenre")
                .<Genre, GenreMongo>chunk(CHUNK_SIZE)
                .reader(itemReaderCreator.getReaderByRepository(genreRepository))
                .processor((ItemProcessor<Genre, GenreMongo>)item -> new GenreMongo(String.valueOf(item.getId()), item.getName()))
                .writer(genreItemWriter)
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    public Step copyBookStep(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("copyBook")
                .<Book, BookMongo>chunk(CHUNK_SIZE)
                .reader(itemReaderCreator.getReaderByRepository(bookRepository))
                .processor(bookItemProcessor)
                .writer(new RepositoryItemWriterBuilder<BookMongo>().repository(bookRepositoryMongo)
                                                                    .methodName("save")
                                                                    .build())
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    StepExecutionListener stepExecutionListener()
    {
        return new StepExecutionListener() {
            @Override
            public void beforeStep(StepExecution stepExecution) {
                logger.debug(stepExecution.getStepName() + " step started");
            }

            @Override
            public ExitStatus afterStep(StepExecution stepExecution) {
                logger.debug(stepExecution.getStepName() + " step finished");
                return ExitStatus.COMPLETED;
            }
        };
    }

    @Bean
    JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(@NonNull JobExecution jobExecution) {
                logger.debug("job started");
                idRelationRepository.deleteAll();
            }

            @Override
            public void afterJob(@NonNull JobExecution jobExecution) {
                //mongoTemplate.dropCollection(IdRelation.class);
                logger.debug("job finished");
            }
        };
    }

    @Bean
    public JobRegistryBeanPostProcessor postProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

}
