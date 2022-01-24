package ru.otus.spring.hw18.security.jwt;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

public interface JwtUtils {

    String generateJwtToken(Authentication authentication);
    String getUserNameFromJwtToken(String token);
    boolean validateJwtToken(String authToken);
    Optional<String> parseJwt(HttpServletRequest request);
    Date getExpiredFromJwtToken(String token);

}
