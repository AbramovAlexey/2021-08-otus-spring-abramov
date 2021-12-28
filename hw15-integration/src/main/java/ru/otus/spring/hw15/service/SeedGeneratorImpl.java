package ru.otus.spring.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Color;
import ru.otus.spring.hw15.domain.Seed;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class SeedGeneratorImpl implements SeedGenerator{

    private Random random = new Random();

    @Override
    public List<Seed> generate() {
        return IntStream.range(0, random.nextInt(2,10))
                        .mapToObj(it -> new Seed(random.nextFloat(),false, false, getRandomColor()))
                        .toList();
    }

    private Color getRandomColor() {
        var index = random.nextInt(Color.values().length);
        return Color.values()[index];
    }

}
