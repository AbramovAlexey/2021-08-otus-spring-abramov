package ru.otus.spring.hw15.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class FlowCommands {

    @ShellMethod(value = "Start integration flow", key = {"startFlow"})
    public String startFlow(){
        return "ok";
    }
}
