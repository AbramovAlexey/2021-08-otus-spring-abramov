package ru.otus.spring.hw4.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw4.config.ExamConfig;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Setter
@Getter
public class SourceReaderImpl implements SourceReader {

    private String fileName;

    public SourceReaderImpl(ExamConfig examConfig) {
        this.fileName = examConfig.getCsvFilename();
    }

    @Override
    public List<String> getSourceRows() {
        List<String> stringList =  null;
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name must not be null");
        }
       try (InputStream inputStream = this.getClass()
                                     .getClassLoader()
                                     .getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
       ) {
           stringList = bufferedReader.lines()
                                      .collect(Collectors.toList());
       } catch (IOException e) {
           throw new UncheckedIOException("Unable to read source file", e);
       }
        return stringList;
    }
}