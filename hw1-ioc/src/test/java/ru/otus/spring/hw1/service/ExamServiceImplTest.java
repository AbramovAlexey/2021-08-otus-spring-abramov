package ru.otus.spring.hw1.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.hw1.dao.ExamDaoCsv;
import ru.otus.spring.hw1.domain.Exam;

@ExtendWith(MockitoExtension.class)
public class ExamServiceImplTest {

    @Spy
    private ExamDaoCsv examDaoCsv;

    @InjectMocks
    private ExamServiceImpl examService;

    @Test
    void allRowsLoaded() throws Exception {
        examDaoCsv.setFileName("exam-questions.csv");
        Exam exam = examService.loadFromFile();
        Assertions.assertThat(exam).isNotNull();
        Assertions.assertThat(exam.getQuestions()).hasSize(3);
    }

    @Test
    void shouldRaiseExceptionWhenFileNotExists(){
        Assertions.assertThatIllegalArgumentException()
                  .isThrownBy(examService::loadFromFile);
    }


}
