package ru.otus.spring.hw18.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "tokenBlacklist")
public class BlackListTokenItem {

    public BlackListTokenItem(String token, Date expireTo) {
        this.token = token;
        this.expireTo = expireTo;
    }

    @Id
    private String id;
    private String token;
    private Date expireTo;

}
