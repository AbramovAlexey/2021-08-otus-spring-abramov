package ru.otus.spring.hw17.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw17.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    Optional<AppUser> findByName(String name);
    Long countAllByRolesContains(String id);

}
