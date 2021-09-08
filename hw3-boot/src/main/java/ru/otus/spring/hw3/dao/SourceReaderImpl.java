package ru.otus.spring.hw3.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw3.config.ExamConfig;

import java.io.InputStream;

@Component
@Setter
@Getter
public class SourceReaderImpl implements SourceReader {

    private String fileName;

    public SourceReaderImpl(ExamConfig examConfig) {
        this.fileName = examConfig.getCsvFilename();
    }

    @Override
    public InputStream getSourceStream() {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name must not be null");
        }
        return this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);
    }

}