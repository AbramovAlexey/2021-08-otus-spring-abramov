package ru.otus.spring.hw4.dao;

import ru.otus.spring.hw4.domain.Exam;

import java.util.List;

public interface ExamTransformer {

    Exam transform(List<String> rows);

}
