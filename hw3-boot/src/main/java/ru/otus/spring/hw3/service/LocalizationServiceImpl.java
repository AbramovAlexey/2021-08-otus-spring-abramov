package ru.otus.spring.hw3.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw3.config.AppConfig;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    private Locale locale;
    private MessageSource messageSource;

    public LocalizationServiceImpl(AppConfig appConfig, MessageSource messageSource) {
        if (appConfig.getLocaleTag() == null) {
            this.locale = LocaleContextHolder.getLocale();
        } else {
            this.locale = Locale.forLanguageTag(appConfig.getLocaleTag());
        }
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageLabel, String[] params) {
        return messageSource.getMessage(messageLabel, params, locale);
    }

    @Override
    public String getMessage(String messageLabel) {
        return getMessage(messageLabel, null);
    }

}
