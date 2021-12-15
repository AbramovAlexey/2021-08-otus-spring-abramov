package ru.otus.spring.hw13.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class AppUser {

    public AppUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    private String id;
    private String name;
    private String password;

}
