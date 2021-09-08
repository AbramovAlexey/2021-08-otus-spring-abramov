package ru.otus.spring.hw2.service;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw2.dao.*;
import ru.otus.spring.hw2.domain.Exam;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExamServiceImplTest {

    private SourceReaderImpl sourceReader = new SourceReaderImpl();
    private ExamTransformer examTransformer = new ExamTransformerCsv(sourceReader);
    private ExamDaoFile examDaoFile = new ExamDaoFile(examTransformer);
    private ExamServiceImpl examService = new ExamServiceImpl(examDaoFile);

    @BeforeEach
    void setFileName(){
        sourceReader.setFileName("exam-questions.csv");
    }

    @Test
    void allRowsLoaded() {
        Exam exam = examService.loadFromSource();
        Assertions.assertThat(exam).isNotNull();
        Assertions.assertThat(exam.getQuestions()).hasSize(5);
    }

    @Test
    void shouldRaiseExceptionWhenFileNotExists(){
        sourceReader.setFileName("");
        Assertions.assertThatIllegalArgumentException()
                  .isThrownBy(examService::loadFromSource);
    }

    @SneakyThrows
    @Test
    void shouldExamExecuted() {
        examService.setMinScore(1);
        List<String> answers = new ArrayList<>();
        answers.add("Ivan Ivanov");
        answers.add("1");
        answers.add("2");
        answers.add("3");
        answers.add("1");
        answers.add("2");
        //Scanner нельзя "замокировать", подменим для него входной поток
        System.setIn(new ByteArrayInputStream(answers.stream()
                                                     .collect(Collectors.joining(System.lineSeparator()))
                                                     .getBytes()));
        Assertions.assertThat(examService.startExam())
                  .isTrue();
    }

}
