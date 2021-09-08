package ru.otus.spring.hw3.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw3.config.AppConfig;
import ru.otus.spring.hw3.domain.Exam;
import ru.otus.spring.hw3.domain.Question;
import ru.otus.spring.hw3.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

@Setter
@Getter
@Repository
public class ExamDaoCsv implements ExamDao {

    private String fileName;
    private CsvMapper csvMapper = new CsvMapper();
    private CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

    public ExamDaoCsv(AppConfig examConfig) {
        this.fileName = examConfig.getCsvFilename();
    }

    @Override
    public Exam loadFromFile() {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name must not be null");
        }
        Exam newExam = new Exam();
        try (InputStream inputStream = Utils.getResourceByName(fileName)) {
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
