package ru.otus.spring.hw13.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtUtils {

    String generateJwtToken(Authentication authentication);
    String getUserNameFromJwtToken(String token);
    boolean validateJwtToken(String authToken);

}
