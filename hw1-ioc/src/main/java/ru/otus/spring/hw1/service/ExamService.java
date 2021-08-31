package ru.otus.spring.hw1.service;


import ru.otus.spring.hw1.domain.Exam;

public interface ExamService {

    public Exam loadFromFile() throws Exception;

}
