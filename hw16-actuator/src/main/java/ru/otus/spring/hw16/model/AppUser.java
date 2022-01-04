package ru.otus.spring.hw16.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class AppUser {

    public AppUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AppUser(String name, String password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Id
    private String id;
    private String name;
    private String password;
    @DBRef
    private List<Role> roles = new ArrayList<>();

}
