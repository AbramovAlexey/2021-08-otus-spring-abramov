package ru.otus.spring.hw15.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.hw15.domain.Seed;
import ru.otus.spring.hw15.domain.Vegetable;
import ru.otus.spring.hw15.integration.GreenhouseGateway;
import ru.otus.spring.hw15.service.SeedGenerator;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class FlowCommands {

    private final GreenhouseGateway greenhouse;
    private final SeedGenerator seedGenerator;

    @ShellMethod(value = "Start integration flow", key = {"startFlow"})
    public String startFlow(){
        return greenhouse.process(seedGenerator.generate()).stream()
                .map(Vegetable::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
