package ru.otus.spring.hw16.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.otus.spring.hw16.repository.BlackListTokenItemRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JwtUtilsImpl implements JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtilsImpl.class);
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer";
    private static final Pattern TOKEN_PATTERN = Pattern.compile(BEARER_PREFIX + " (.+)$");

    private final JwtConfig jwtConfig;
    private final BlackListTokenItemRepository blackListTokenItemRepository;

    @Override
    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = now.plus(Duration.ofMillis(jwtConfig.getExpTime()));
        return Jwts.builder()
                   .setSubject(userPrincipal.getUsername())
                   .setIssuedAt(convertToDate(now))
                   .setExpiration(convertToDate(expire))
                   .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                   .compact();
    }

    @Override
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                   .setSigningKey(jwtConfig.getSecret())
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    @Override
    public Date getExpiredFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    @Override
    public boolean validateJwtToken(String authToken) {
        try {
            if (blackListTokenItemRepository.findByToken(authToken).isPresent()) {
                logger.error("JWT token in black list");
                return false;
            }
            Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<String> parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(headerAuth)) {
            Matcher matcher = TOKEN_PATTERN.matcher(headerAuth);
            if (matcher.find()) {
                return Optional.of(matcher.group(1));
            }
        }
        return Optional.empty();
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}