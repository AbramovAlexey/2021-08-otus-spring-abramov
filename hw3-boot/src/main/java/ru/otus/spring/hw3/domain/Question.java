package ru.otus.spring.hw3.domain;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Question {

    private String formulation ;
    private String variant1;
    private String variant2;
    private String variant3;
    private String answer;
    private Map<Integer, String> mapVariants;

}
