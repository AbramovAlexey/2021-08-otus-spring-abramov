package ru.otus.spring.hw1.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Question {

    private String formulation ;
    private String variant1;
    private String variant2;
    private String variant3;
    private String answer;

    public void print(){
        System.out.println(formulation);
        System.out.println(variant1);
        System.out.println(variant2);
        System.out.println(variant3);
    }

}
