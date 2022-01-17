package ru.otus.spring.hw17.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw17.model.Role;
import ru.otus.spring.hw17.repository.AppUserRepository;
import ru.otus.spring.hw17.repository.RoleRepository;
import ru.otus.spring.hw17.security.config.RoleNames;

@Component
@RequiredArgsConstructor
public class LibraryUsageHealthIndicator implements HealthIndicator {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public Health health() {
        return  getUsersCount() > 0 ? Health.up().withDetail("message", "Library has users").build()
                                    : Health.down().withDetail("message", "Our library without users!!").build();
    }

    private Long getUsersCount() {
        Role userRole = roleRepository.findByName(RoleNames.USER).orElseThrow(() -> new RuntimeException(RoleNames.USER + " not exist!!"));
        return appUserRepository.countAllByRolesContains(userRole.getId());
    }

}
