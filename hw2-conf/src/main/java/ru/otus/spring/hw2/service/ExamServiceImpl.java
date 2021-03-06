package ru.otus.spring.hw2.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw2.dao.ExamDao;
import ru.otus.spring.hw2.domain.Exam;
import ru.otus.spring.hw2.domain.Question;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


@Getter
@Setter
@Service
public class ExamServiceImpl implements ExamService {

    private ExamDao examDao;

    @Value("${exam.minscore}")
    private int minScore;

    public ExamServiceImpl(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    public Exam loadFromSource(){
        return examDao.loadFromSource();
    }

    @Override
    public boolean startExam() {
        Exam exam = loadFromSource();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start testing.");
        System.out.println("Enter your full name");
        String studentName = scanner.nextLine();
        int size = exam.getQuestions().size();
        int rightAnswers = 0;
        for (int i = 0; i < size ; i++) {
            int userAnswer;
            Question question = exam.getQuestions().get(i);
            System.out.println("Question №"+ (i + 1) + " of " + size);
            ask(question);
            try {
                userAnswer = Integer.parseInt(scanner.nextLine());
                rightAnswers += checkAnswer(question, userAnswer) ? 1 : 0;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Wrong input, answer ignored");
            }
            System.out.println();
        }
        System.out.println("Correct answers - " + rightAnswers + ".");
        if (rightAnswers >= minScore) {
            System.out.println(studentName + " passed test.");
            return true;
        } else {
            System.out.println(studentName + " failed test.");
            return false;
        }
    }

    private void ask(Question question) {
        System.out.println(question.getFormulation());
        if (question.getMapVariants() == null) {
            Map mapVariants = new LinkedHashMap<>();
            mapVariants.put(1, question.getVariant1());
            mapVariants.put(2, question.getVariant2());
            mapVariants.put(3, question.getVariant3());
            question.setMapVariants(mapVariants);
        }
        question.getMapVariants()
                .entrySet()
                .forEach(entry -> System.out.println(entry.getKey() + ". " + entry.getValue()));
    }

    private boolean checkAnswer(Question question, int userAnswer) {
        String variant = question.getMapVariants().get(userAnswer);
        return variant == null ? false : variant.equals(question.getAnswer());
    }

}
