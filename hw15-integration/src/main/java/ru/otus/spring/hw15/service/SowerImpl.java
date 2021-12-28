package ru.otus.spring.hw15.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Seed;

@Service
public class SowerImpl implements Sower{

    private final Logger logger = LoggerFactory.getLogger(SowerImpl.class);

    @Override
    public Seed sow(Seed seed) {
        seed.setSeeded(true);
        logger.info("Seed seeded " + seed);
        return seed;
    }

}
