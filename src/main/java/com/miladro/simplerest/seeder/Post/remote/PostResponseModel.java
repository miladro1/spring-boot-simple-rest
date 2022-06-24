package com.miladro.simplerest.seeder.Post.remote;

import lombok.Data;

@Data
public class PostResponseModel {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
