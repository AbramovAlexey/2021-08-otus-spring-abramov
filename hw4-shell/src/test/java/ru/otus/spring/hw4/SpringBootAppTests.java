package ru.otus.spring.hw4;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.hw4.dao.SourceReaderImpl;
import ru.otus.spring.hw4.domain.Exam;
import ru.otus.spring.hw4.service.ExamServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(properties = {
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class SpringBootAppTests {

	private static final InputStream DEFAULT_STDIN = System.in;

	@Autowired
	private SourceReaderImpl sourceReader;

	@Autowired
	private ExamServiceImpl examService;

	@Test
	void allRowsLoaded() {
		Exam exam = examService.loadFromFile();
		Assertions.assertThat(exam).isNotNull();
		Assertions.assertThat(exam.getQuestions()).hasSize(5);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void shouldRaiseExceptionWhenFileNotExists(){
		String name = sourceReader.getFileName();
		sourceReader.setFileName("");
		Assertions.assertThatIllegalArgumentException()
				.isThrownBy(examService::loadFromFile);
		sourceReader.setFileName(name);
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
		answers.add("1");
		//Scanner нельзя "замокировать", подменим для него входной поток
		System.setIn(new ByteArrayInputStream(answers.stream()
				.collect(Collectors.joining(System.lineSeparator()))
				.getBytes()));
		Assertions.assertThat(examService.startExam())
				.isTrue();
	}

	@AfterAll
	public static void restoreStdin() {
		System.setIn(DEFAULT_STDIN);
	}

}
