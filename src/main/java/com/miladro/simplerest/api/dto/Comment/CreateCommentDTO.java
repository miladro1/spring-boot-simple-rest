package com.miladro.simplerest.api.dto.Comment;

import lombok.Data;

@Data
public class CreateCommentDTO {
    //TODO: add validation
    private Long postId;
    private String name;
    private String email;
    private String body;
}
