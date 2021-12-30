package ru.otus.spring.hw16.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.hw16.model.AppUser;
import ru.otus.spring.hw16.model.Role;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Repository AppUser must")
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Role role = new Role("ROLE_USER");
    private AppUser user = new AppUser("user", "user", List.of(role));

    @Test
    @DisplayName("correct count user by role")
    public void mustCorrectCountUserByRole() {
        role = roleRepository.save(role);
        assertThat(appUserRepository.countAllByRolesContains(role.getId())).isEqualTo(0);
        appUserRepository.save(user);
        assertThat(appUserRepository.countAllByRolesContains(role.getId())).isEqualTo(1);
    }
}
