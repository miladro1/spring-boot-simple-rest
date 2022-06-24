package com.miladro.simplerest.api.dto.Post;

import lombok.Data;

@Data
public class PostDTO {
    private String id;
    private String userId;
    private String title;
    private String body;
}
