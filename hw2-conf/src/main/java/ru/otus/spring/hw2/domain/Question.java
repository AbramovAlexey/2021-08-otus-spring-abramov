package ru.otus.spring.hw2.domain;

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

    public void ask() {
        System.out.println(getFormulation());
        initMapVariants();
        mapVariants.entrySet()
                   .forEach(entry -> System.out.println(entry.getKey() + ". " + entry.getValue()));
    }

    public boolean checkAnswer(int userAnswer) {
        String variant = mapVariants.get(userAnswer);
        return variant == null ? false : variant.equals(answer);
    }

    private void initMapVariants() {
        if (mapVariants == null) {
            mapVariants = new LinkedHashMap<>();
            mapVariants.put(1, variant1);
            mapVariants.put(2, variant2);
            mapVariants.put(3, variant3);
        }
    }

}
