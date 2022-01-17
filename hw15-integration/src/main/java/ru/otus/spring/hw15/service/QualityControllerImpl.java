package ru.otus.spring.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Vegetable;

@Service
public class QualityControllerImpl implements QualityController{

    private static final float MIN_WEIGHT = 1f;

    @Override
    public boolean passed(Vegetable vegetable) {
        return vegetable.weight() > MIN_WEIGHT;
    }

}
