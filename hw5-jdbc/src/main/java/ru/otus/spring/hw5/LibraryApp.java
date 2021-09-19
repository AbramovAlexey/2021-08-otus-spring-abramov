package ru.otus.spring.hw5;

import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApp {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(LibraryApp.class, args);
		Console.main(args);
	}

}
