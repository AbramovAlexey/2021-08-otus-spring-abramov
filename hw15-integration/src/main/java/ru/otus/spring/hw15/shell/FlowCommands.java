package ru.otus.spring.hw15.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;
import ru.otus.spring.hw15.domain.Vegetable;
import ru.otus.spring.hw15.integration.GreenhouseGateway;
import ru.otus.spring.hw15.service.SeedGenerator;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class FlowCommands implements Quit.Command{

    private final GreenhouseGateway greenhouse;
    private final SeedGenerator seedGenerator;

    @ShellMethod(value = "Start integration flow", key = {"startFlow"})
    public String startFlow(){
        return greenhouse.process(seedGenerator.generate()).stream()
                .map(Vegetable::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(value = "Exit", key = {"exit"})
    public void exit(){
        System.exit(0);
    }

}
