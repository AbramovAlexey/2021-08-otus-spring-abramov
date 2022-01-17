package ru.otus.spring.hw17.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw17.dto.AppUserDto;
import ru.otus.spring.hw17.dto.JwtResponse;
import ru.otus.spring.hw17.model.BlackListTokenItem;
import ru.otus.spring.hw17.repository.BlackListTokenItemRepository;
import ru.otus.spring.hw17.security.jwt.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final BlackListTokenItemRepository repository;

    @PostMapping("/api/auth/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody AppUserDto appUserDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUserDto.getName(), appUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                                                 userDetails.getUsername(),
                                                 userDetails.getAuthorities().stream()
                                                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        jwtUtils.parseJwt(request).ifPresent(token -> {
            Date expire = jwtUtils.getExpiredFromJwtToken(token);
            repository.save(new BlackListTokenItem(token, expire));
        });
        return ResponseEntity.ok().build();
    }

}