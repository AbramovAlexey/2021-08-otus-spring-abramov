package ru.otus.spring.hw18.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.model.AppUser;
import ru.otus.spring.hw18.model.Role;
import ru.otus.spring.hw18.repository.AppUserRepository;

import java.util.stream.Collectors;

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
        return new User(appUser.getName(),
                        appUser.getPassword(),
                        appUser.getRoles().stream()
                                          .map(Role::getName)
                                          .map(SimpleGrantedAuthority::new)
                                          .collect(Collectors.toList()));
    }

}
