package ru.otus.spring.hw15.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Seed;

import java.util.Random;

@Service
public class GrowerImpl implements Grower{

    private final Logger logger = LoggerFactory.getLogger(GrowerImpl.class);
    private final Random random = new Random();

    @Override
    public Seed grow(Seed seed) {
        water(seed);
        cultivate(seed);
        seed.setGrown(true);
        return seed;
    }

    private void water(Seed seed) {
        seed.setWeight(seed.getWeight() + random.nextFloat());
        logger.info("watered " + seed);
    }

    private void cultivate(Seed seed) {
        seed.setWeight(seed.getWeight() + random.nextFloat());
        logger.info("cultivated " + seed);
    }

}
