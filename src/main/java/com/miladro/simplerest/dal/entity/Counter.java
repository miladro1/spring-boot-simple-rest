package com.miladro.simplerest.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Counter {
    @Id
    private String id;
    private Long postCounter;
    private Long commentsCounter;
    private Long usersCounter;
    @Transient
    private Long count;
}
