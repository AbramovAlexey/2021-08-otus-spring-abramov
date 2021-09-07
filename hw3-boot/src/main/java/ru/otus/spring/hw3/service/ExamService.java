package ru.otus.spring.hw3.service;

import ru.otus.spring.hw3.domain.Exam;

public interface ExamService {

    Exam loadFromFile() ;

    boolean startExam();

}
