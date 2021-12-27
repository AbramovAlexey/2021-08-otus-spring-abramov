package ru.otus.spring.hw14.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "idRelationsTemp")
@NoArgsConstructor
@Getter
@Setter
public class IdRelation {

    @Id
    String id;
    long sqlId;
    String mongoId;
    String type;

}
