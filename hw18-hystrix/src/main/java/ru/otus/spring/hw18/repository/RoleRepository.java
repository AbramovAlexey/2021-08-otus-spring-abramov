package ru.otus.spring.hw18.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw18.model.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    @Cacheable(cacheNames = "roleCache")
    Optional<Role> findByName(String name);

}
