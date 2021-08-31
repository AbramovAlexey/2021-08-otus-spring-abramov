package ru.otus.spring.hw1.service;

import ru.otus.spring.hw1.domain.Exam;

public interface ExamService {

    Exam loadFromFile() throws Exception;

}
