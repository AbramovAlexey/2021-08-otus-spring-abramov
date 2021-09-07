package ru.otus.spring.hw3;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw3.dao.ExamDaoCsv;
import ru.otus.spring.hw3.domain.Exam;
import ru.otus.spring.hw3.service.ExamServiceImpl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SpringBootAppTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ExamDaoCsv examDaoCsv;

	@Autowired
	private ExamServiceImpl examService;

	@Test
	void allRowsLoaded() {
		Exam exam = examService.loadFromFile();
		Assertions.assertThat(exam).isNotNull();
		Assertions.assertThat(exam.getQuestions()).hasSize(2);
	}

	@Test
	void shouldRaiseExceptionWhenFileNotExists(){
		String name = examDaoCsv.getFileName();
		examDaoCsv.setFileName("");
		Assertions.assertThatIllegalArgumentException()
				.isThrownBy(examService::loadFromFile);
		examDaoCsv.setFileName(name);
	}

	@SneakyThrows
	@Test
	void shouldExamExecuted() {
		examService.setMinScore(1);
		List<String> answers = new ArrayList<>();
		answers.add("Ivan Ivanov");
		answers.add("3");
		answers.add("1");
		//Scanner нельзя "замокировать", подменим для него входной поток
		System.setIn(new ByteArrayInputStream(answers.stream()
				.collect(Collectors.joining(System.lineSeparator()))
				.getBytes()));
		Assertions.assertThat(examService.startExam())
				.isTrue();
	}

}
