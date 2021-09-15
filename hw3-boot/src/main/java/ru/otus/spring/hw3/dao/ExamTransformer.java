package ru.otus.spring.hw3.dao;

import ru.otus.spring.hw3.domain.Exam;

import java.util.List;

public interface ExamTransformer {

    Exam transform(List<String> rows);

}
