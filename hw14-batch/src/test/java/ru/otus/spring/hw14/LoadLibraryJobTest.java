package ru.otus.spring.hw14;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw14.repository.*;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.hw14.job.JobConfig.JOB_NAME;

@SpringBootTest
@SpringBatchTest
public class LoadLibraryJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    AuthorRepositoryMongo authorRepositoryMongo;
    @Autowired
    GenreRepositoryMongo genreRepositoryMongo;
    @Autowired
    BookRepositoryMongo bookRepositoryMongo;

    @Test
    void testJob() throws Exception {
        assertThat(jobLauncherTestUtils.getJob()).isNotNull()
                        .extracting(Job::getName)
                        .isEqualTo(JOB_NAME);
        assertThat(jobLauncherTestUtils.launchJob()
                                       .getExitStatus()
                                       .getExitCode())
                                       .isEqualTo(ExitStatus.COMPLETED.getExitCode());
        assertThat(bookRepositoryMongo.count()).isEqualTo(bookRepository.count());
        assertThat(authorRepositoryMongo.count()).isEqualTo(authorRepository.count());
        assertThat(genreRepositoryMongo.count()).isEqualTo(genreRepository.count());
    }

}
