package ru.otus.spring.hw13.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "roles")
public class Role {

    public Role(String name) {
        this.name = name;
    }

    @Id
    private String id;
    private String name;

}
