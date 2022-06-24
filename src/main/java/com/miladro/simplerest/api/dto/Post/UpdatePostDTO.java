package com.miladro.simplerest.api.dto.Post;

import lombok.Data;

@Data
public class UpdatePostDTO {
    //TODO: add validation
    private String title;
    private String body;
}
