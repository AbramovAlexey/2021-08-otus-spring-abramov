package ru.otus.spring.hw1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.hw1.domain.Exam;
import ru.otus.spring.hw1.service.ExamService;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ExamService examService = context.getBean(ExamService.class);
        Exam exam = examService.loadFromFile();
        exam.printQuestions();
    }

}
