package ru.otus.spring.hw13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw13.model.AppUser;
import ru.otus.spring.hw13.repository.AppUserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByName(name)
                                           .orElseThrow(() -> new UsernameNotFoundException("User with username: " + name + " not found"));
        return toUserDetails(appUser);
    }

    private UserDetails toUserDetails(AppUser appUser) {
        return new User(appUser.getName(), appUser.getPassword(), Collections.emptyList());
    }

}
