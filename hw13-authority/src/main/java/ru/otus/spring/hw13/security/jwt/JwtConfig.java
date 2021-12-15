package ru.otus.spring.hw13.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private Long expTime;

}
