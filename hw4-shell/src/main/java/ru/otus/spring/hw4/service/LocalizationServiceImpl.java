package ru.otus.spring.hw4.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    private final Locale locale = LocaleContextHolder.getLocale();
    private final MessageSource messageSource;

    public LocalizationServiceImpl(MessageSource messageSource) {
       this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageLabel, Object ... params) {
        String[] parameters = null;
        if (params != null) {
            parameters = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                parameters[i] = String.valueOf(params[i]);
            }
        }
        return messageSource.getMessage(messageLabel, parameters, locale);
    }

    @Override
    public String getMessage(String messageLabel) {
        return getMessage(messageLabel, null);
    }

}
