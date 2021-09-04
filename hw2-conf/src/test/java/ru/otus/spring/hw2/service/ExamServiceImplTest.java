package ru.otus.spring.hw2.service;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.hw2.dao.ExamDaoCsv;
import ru.otus.spring.hw2.domain.Exam;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class ExamServiceImplTest {

    @Spy
    private ExamDaoCsv examDaoCsv;

    @InjectMocks
    private ExamServiceImpl examService;

    @BeforeEach
    void setFileName(){
        examDaoCsv.setFileName("exam-questions.csv");
    }

    @Test
    void allRowsLoaded() {
        Exam exam = examService.loadFromFile();
        Assertions.assertThat(exam).isNotNull();
        Assertions.assertThat(exam.getQuestions()).hasSize(5);
    }

    @Test
    void shouldRaiseExceptionWhenFileNotExists(){
        examDaoCsv.setFileName("");
        Assertions.assertThatIllegalArgumentException()
                  .isThrownBy(examService::loadFromFile);
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
