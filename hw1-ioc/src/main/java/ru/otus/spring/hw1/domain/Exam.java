package ru.otus.spring.hw1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam {

    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
       questions.add(question);
    }

    public void printQuestions() {
        for (int i = 0; i < questions.size() ; i++) {
            System.out.println(String.format("Question №%d:", i + 1));
            questions.get(i).print();
            System.out.println();
        }
    }

}
