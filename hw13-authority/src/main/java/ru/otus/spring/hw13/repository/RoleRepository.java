package ru.otus.spring.hw13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw13.model.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(String name);

}
