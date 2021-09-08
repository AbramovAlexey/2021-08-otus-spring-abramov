package ru.otus.spring.hw3.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Exam {

    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
       questions.add(question);
    }

}
