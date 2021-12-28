package ru.otus.spring.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Seed;
import ru.otus.spring.hw15.domain.Vegetable;

@Service
public class HarvesterImpl implements Harvester{

    @Override
    public Vegetable harvest(Seed seed) {
        return new Vegetable(seed.getWeight(), seed.getColor());
    }

}
