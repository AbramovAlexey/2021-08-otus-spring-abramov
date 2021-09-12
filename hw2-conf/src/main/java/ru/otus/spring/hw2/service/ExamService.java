package ru.otus.spring.hw2.service;

import ru.otus.spring.hw2.domain.Exam;

public interface ExamService {

    Exam loadFromSource() ;

    boolean startExam();

}
