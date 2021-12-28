package ru.otus.spring.hw15.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Seed;

@Service
public class GrowerImpl implements Grower{

    private final Logger logger = LoggerFactory.getLogger(GrowerImpl.class);

    @Override
    public Seed grow(Seed seed) {
        water(seed);
        cultivate(seed);
        seed.setGrowed(true);
        return seed;
    }

    private void water(Seed seed) {
        logger.info("Seed watered " + seed);
    }

    private void cultivate(Seed seed) {
        logger.info("Seed cultivated " + seed);
    }

}
