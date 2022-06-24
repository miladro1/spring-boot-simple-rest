package com.miladro.simplerest.api.dto.Comment;

import lombok.Data;

@Data
public class CommentDTO {
    //TODO: change long ids to String
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
