package ru.otus.spring.hw14.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static ru.otus.spring.hw14.job.JobConfig.JOB_NAME;

@ShellComponent
@RequiredArgsConstructor
public class JobCommands {

    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    @ShellMethod(value = "Start job", key = "start")
    public String startJob() throws Exception {
        return jobOperator.getSummary(jobOperator.start(JOB_NAME,""));
    }

    @ShellMethod(value = "Restart job", key = "restart")
    public String restartJob() throws Exception {
            return jobExplorer.getJobInstanceCount(JOB_NAME) == 0 ? "Nothing to restart, no job instances"
                                                                  : jobOperator.getSummary(jobOperator.startNextInstance(JOB_NAME));
    }

}
