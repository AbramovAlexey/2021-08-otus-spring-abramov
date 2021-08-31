package ru.otus.spring.hw1.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.spring.hw1.domain.Exam;
import ru.otus.spring.hw1.domain.Question;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ExamDaoCsv implements ExamDao {

    private String fileName;
    private CsvMapper csvMapper = new CsvMapper();
    private CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

    @Override
    public Exam loadFromFile() throws IOException {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name must not be null");
        }
        Exam newExam = new Exam();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {

            MappingIterator<Question> questionList = csvMapper.readerFor(Question.class)
                    .with(csvSchema)
                    .readValues(inputStream);
            questionList.forEachRemaining(newExam::addQuestion);
        }
        return newExam;
    }

}
