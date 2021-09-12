package ru.otus.spring.hw2.dao;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw2.domain.Exam;

@Setter
@Repository
public class ExamDaoFile implements ExamDao {

    private ExamTransformer examTransformer;

    public ExamDaoFile(ExamTransformer examTransformer) {
        this.examTransformer = examTransformer;
    }

    @Override
    public Exam loadFromSource() {
        return examTransformer.transform();
    }
}
