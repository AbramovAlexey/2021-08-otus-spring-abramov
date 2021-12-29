package ru.otus.spring.hw15.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seed{

    private float weight;
    private boolean seeded = false;
    private boolean grown = false;
    private Color color;

}
