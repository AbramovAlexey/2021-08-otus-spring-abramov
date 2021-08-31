package ru.otus.spring.hw1.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.spring.hw1.dao.ExamDao;
import ru.otus.spring.hw1.domain.Exam;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ExamServiceImpl implements ExamService {

    ExamDao examDao;

    @Override
    public Exam loadFromFile() throws IOException {
        return examDao.loadFromFile();
    }

}
