package ru.otus.spring.hw14.job;

import com.mongodb.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.model.*;
import ru.otus.spring.hw14.repository.*;

import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfig {

    private static final int CHUNK_SIZE = 2;
    private final Map<String, Sort.Direction> sort = Map.of("id", Sort.Direction.ASC);

    private final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AuthorRepository authorRepository;
    private final AuthorRepositoryMongo authorRepositoryMongo;
    private final GenreRepository genreRepository;
    private final GenreRepositoryMongo genreRepositoryMongo;
    private final BookRepository bookRepository;
    private final BookRepositoryMongo bookRepositoryMongo;

    @Bean
    public Job importBookJob(Step copyAuthorStep, Step copyGenreStep, JobExecutionListener jobExecutionListener) {
        return jobBuilderFactory.get("copyLibrary")
                .flow(copyAuthorStep)
                .next(copyGenreStep)
                .end()
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step copyAuthorStep(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("copyAuthor")
                .<Author, AuthorMongo>chunk(CHUNK_SIZE)
                .reader(getReaderByRepository(authorRepository))
                .processor((ItemProcessor<Author, AuthorMongo>)item -> new AuthorMongo(item.getFullName()))
                .writer(getWriterByRepository(authorRepositoryMongo))
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    public Step copyGenreStep(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("copyGenre")
                .<Genre, GenreMongo>chunk(CHUNK_SIZE)
                .reader(getReaderByRepository(genreRepository))
                .processor((ItemProcessor<Genre, GenreMongo>)item -> new GenreMongo(item.getName()))
                .writer(getWriterByRepository(genreRepositoryMongo))
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    public Step copyBookStep(StepExecutionListener stepExecutionListener, ItemProcessor<Book, BookMongo> bookItemProcessor) {
        return stepBuilderFactory.get("copyBook")
                .<Book, BookMongo>chunk(CHUNK_SIZE)
                .reader(getReaderByRepository(bookRepository))
                .processor(bookItemProcessor)
                .writer(getWriterByRepository(bookRepositoryMongo))
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
                logger.debug(jobExecution.getJobConfigurationName() + " job started");
            }

            @Override
            public void afterJob(@NonNull JobExecution jobExecution) {
                logger.debug(jobExecution.getJobConfigurationName() + " job finished");
            }
        };
    }

    @Bean
    ItemProcessor<Book, BookMongo> bookItemProcessor() {
        return new ItemProcessor<Book, BookMongo>() {
            @Override
            public BookMongo process(Book item) throws Exception {
                return null;
            }
        };
    }

    private <T> RepositoryItemReader<T> getReaderByRepository(JpaRepository<T, Long> repository) {
        return new RepositoryItemReaderBuilder<T>().repository(repository)
                                                   .methodName("findAll")
                                                   .sorts(sort)
                                                   .name(repository.getClass().getName())
                                                   .build();
    }

    private <T> RepositoryItemWriter<T> getWriterByRepository(MongoRepository<T, String> repository) {
        return new RepositoryItemWriterBuilder<T>().repository(repository)
                                                .methodName("save")
                                                .build();
    }

}
