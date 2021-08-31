package ru.otus.spring.hw1.dao;

import ru.otus.spring.hw1.domain.Exam;

import java.io.IOException;

public interface ExamDao {

    public Exam loadFromFile() throws IOException;

}
