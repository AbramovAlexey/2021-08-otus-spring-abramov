package ru.otus.spring.hw15.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seed{

    private float weight;
    private boolean seeded = false;
    private boolean growed = false;
    private Color color;

}
