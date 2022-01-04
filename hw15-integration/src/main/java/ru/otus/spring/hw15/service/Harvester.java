package ru.otus.spring.hw15.service;

import ru.otus.spring.hw15.domain.Seed;
import ru.otus.spring.hw15.domain.Vegetable;

public interface Harvester {

    Vegetable harvest(Seed seed);

}
