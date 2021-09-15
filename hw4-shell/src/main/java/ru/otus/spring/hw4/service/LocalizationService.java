package ru.otus.spring.hw4.service;

public interface LocalizationService {

    String getMessage(String messageLabel, Object ... params);
    String getMessage(String messageLabel);

}
