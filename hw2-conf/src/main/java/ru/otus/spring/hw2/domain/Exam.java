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

}
