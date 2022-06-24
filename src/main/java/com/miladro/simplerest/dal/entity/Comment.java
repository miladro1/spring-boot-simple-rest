package com.miladro.simplerest.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class Comment extends Entity {
    @Indexed
    private Long postId;
    private String name;
    private String email;
    private String body;
}
