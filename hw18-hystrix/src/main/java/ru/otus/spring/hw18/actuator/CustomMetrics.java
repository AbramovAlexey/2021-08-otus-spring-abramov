package ru.otus.spring.hw18.actuator;


import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw18.repository.BookRepository;

import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
@RequiredArgsConstructor
public class CustomMetrics implements InitializingBean {

    private static final String GAUGE_BOOKS = "gauge.book";
    private final MeterRegistry registry;
    private final BookRepository bookRepository;
    private AtomicLong count = new AtomicLong();

    @Override
    public void afterPropertiesSet() {
        count.set(bookRepository.count());
        registry.gauge(GAUGE_BOOKS, count);
    }

    @After("@annotation(ru.otus.spring.hw18.actuator.AddBookTracking)")
    public void incrementGauge() {
        registry.gauge(GAUGE_BOOKS, count.incrementAndGet());
    }

    @After("@annotation(ru.otus.spring.hw18.actuator.DeleteBookTracking)")
    public void decrementGauge() {
        registry.gauge(GAUGE_BOOKS, count.decrementAndGet());
    }

}
