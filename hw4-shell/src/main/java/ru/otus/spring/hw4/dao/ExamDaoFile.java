package ru.otus.spring.hw4.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.hw4.domain.Exam;

@Component
public class ExamDaoFile implements ExamDao {

    private final ExamTransformer examTransformer;
    private final SourceReader sourceReader;

    public ExamDaoFile(ExamTransformer examTransformer, SourceReader sourceReader) {
        this.examTransformer = examTransformer;
        this.sourceReader = sourceReader;
    }

    @Override
    public Exam loadFromSource() {
        return examTransformer.transform(sourceReader.getSourceRows());
    }
}