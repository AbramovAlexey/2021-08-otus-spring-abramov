package ru.otus.spring.hw2.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Setter
@Getter
public class SourceReaderImpl implements SourceReader {

    @Value("${csv.filename}")
    private String fileName;

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
