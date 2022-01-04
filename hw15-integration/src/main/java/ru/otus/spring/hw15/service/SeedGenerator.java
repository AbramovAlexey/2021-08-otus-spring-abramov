package ru.otus.spring.hw15.service;

import ru.otus.spring.hw15.domain.Seed;

import java.util.List;

public interface SeedGenerator {

    List<Seed> generate();

}
