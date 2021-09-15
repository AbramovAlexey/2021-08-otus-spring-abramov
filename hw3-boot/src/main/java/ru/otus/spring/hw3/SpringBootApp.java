package ru.otus.spring.hw3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.hw3.service.ExamService;

@SpringBootApplication
public class SpringBootApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootApp.class, args);
		ExamService examService = ctx.getBean(ExamService.class);
		examService.startExam();
	}

}
