package ru.otus.spring.hw1.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.spring.hw1.domain.Exam;
import ru.otus.spring.hw1.domain.Question;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName).getFile()));
        MappingIterator<Question> questionList = csvMapper.readerFor(Question.class)
                                                          .with(csvSchema)
                                                          .readValues(file);
        Exam newExam = new Exam();
        questionList.forEachRemaining(question -> newExam.addQuestion(question));
        return newExam;
    }

}
