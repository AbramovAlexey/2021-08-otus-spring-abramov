package ru.otus.spring.hw2.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class Exam {

    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
       questions.add(question);
    }

    public boolean start(int minScore) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start testing.");
        System.out.println("Enter your full name");
        String studentName = scanner.nextLine();
        int size = questions.size();
        int rightAnswers = 0;
        for (int i = 0; i < size ; i++) {
            int userAnswer;
            Question question = questions.get(i);
            System.out.println("Question №"+ (i + 1) + " of " + size);
            question.ask();
            try {
                userAnswer = Integer.parseInt(scanner.nextLine());
                rightAnswers += question.checkAnswer(userAnswer) ? 1 : 0;
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

}
