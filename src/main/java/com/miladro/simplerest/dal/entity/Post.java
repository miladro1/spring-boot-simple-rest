package com.miladro.simplerest.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class Post extends Entity {
    @Indexed
    private Long userId;
    private String title;
    private String body;
}
