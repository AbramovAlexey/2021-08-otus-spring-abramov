package ru.otus.spring.hw18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.model.BlackListTokenItem;
import ru.otus.spring.hw18.repository.BlackListTokenItemRepository;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlackListTokenItemServiceImpl implements BlackListTokenItemService{

    private BlackListTokenItemRepository blackListTokenItemRepository;

    @HystrixCommand(fallbackMethod = "findByTokenFail")
    public Optional<BlackListTokenItem> findByToken(String token) {
        return blackListTokenItemRepository.findByToken(token);
    }

    private Optional<BlackListTokenItem> findByTokenFail(String token) throws AuthenticationException {
        return Optional.empty();
    }

}
