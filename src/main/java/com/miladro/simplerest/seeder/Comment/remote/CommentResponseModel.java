package com.miladro.simplerest.seeder.Comment.remote;

import lombok.Data;

@Data
public class CommentResponseModel {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
