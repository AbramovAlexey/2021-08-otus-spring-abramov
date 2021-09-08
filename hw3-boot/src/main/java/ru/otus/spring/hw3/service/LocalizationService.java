package ru.otus.spring.hw3.service;

public interface LocalizationService {

    String getMessage(String messageLabel, String[] params);
    String getMessage(String messageLabel);

}
