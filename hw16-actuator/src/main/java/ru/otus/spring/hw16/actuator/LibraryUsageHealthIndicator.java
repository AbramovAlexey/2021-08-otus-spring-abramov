package ru.otus.spring.hw16.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw16.model.Role;
import ru.otus.spring.hw16.repository.AppUserRepository;
import ru.otus.spring.hw16.repository.RoleRepository;

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
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("ROLE_USER not exist!!"));
        return appUserRepository.countAllByRolesContains(userRole.getId());
    }

}
