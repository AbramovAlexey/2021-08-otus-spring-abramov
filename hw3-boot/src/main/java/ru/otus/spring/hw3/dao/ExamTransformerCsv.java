package ru.otus.spring.hw3.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw3.domain.Exam;
import ru.otus.spring.hw3.domain.Question;

import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamTransformerCsv implements ExamTransformer{

    private final CsvMapper csvMapper = new CsvMapper();
    private final CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

    @Override
    public Exam transform(List<String> rows) {
        Exam newExam = new Exam();
        String uniteRows = rows.stream()
                               .collect(Collectors.joining(System.lineSeparator()));
        try {
            MappingIterator<Question> questionList = csvMapper.readerFor(Question.class)
                    .with(csvSchema)
                    .readValues(new StringReader(uniteRows));
            questionList.forEachRemaining(newExam::addQuestion);
        } catch (IOException exception) {
            throw new UncheckedIOException("Unable to parse csv", exception);
        }
        return newExam;
    }

}