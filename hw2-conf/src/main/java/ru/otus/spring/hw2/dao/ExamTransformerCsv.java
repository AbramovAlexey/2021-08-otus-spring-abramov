package ru.otus.spring.hw2.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw2.domain.Exam;
import ru.otus.spring.hw2.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

@Component
public class ExamTransformerCsv implements ExamTransformer{

    private SourceReader sourceReader;
    private CsvMapper csvMapper = new CsvMapper();
    private CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

    public ExamTransformerCsv(SourceReader sourceReader) {
        this.sourceReader = sourceReader;
    }

    @Override
    public Exam transform() {
        Exam newExam = new Exam();
        try (InputStream inputStream = sourceReader.getSourceStream()) {
            MappingIterator<Question> questionList = csvMapper.readerFor(Question.class)
                    .with(csvSchema)
                    .readValues(inputStream);
            questionList.forEachRemaining(newExam::addQuestion);
        } catch (IOException exception) {
            throw new UncheckedIOException("Unable to read source csv file", exception);
        }
        return newExam;
    }

}
