package ru.otus.spring.hw4.service;

import ru.otus.spring.hw4.domain.Exam;

public interface ExamService {

    Exam loadFromFile() ;

    boolean startExam();

}
