package ru.otus.spring.hw18.service;

import ru.otus.spring.hw18.model.BlackListTokenItem;

import java.util.Optional;

public interface BlackListTokenItemService {

    Optional<BlackListTokenItem> findByToken(String token);

}
