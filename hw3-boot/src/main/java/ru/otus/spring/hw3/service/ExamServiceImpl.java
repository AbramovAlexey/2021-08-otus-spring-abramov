package ru.otus.spring.hw3.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw3.dao.ExamDao;
import ru.otus.spring.hw3.domain.Exam;


@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamDao examDao;

    @Value("${exam.minscore}")
    private int minScore;

    @Override
    public Exam loadFromFile(){
        return examDao.loadFromFile();
    }

    @Override
    public boolean startExam() {
        Exam exam = loadFromFile();
        return exam.start(minScore);
    }

}
