package ru.otus.spring.hw4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.hw4.service.ExamService;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {

    private final ExamService examService;

    @ShellMethod(value = "Start exam", key = {"start"})
    public void start() {
       examService.startExam();
    }

}
