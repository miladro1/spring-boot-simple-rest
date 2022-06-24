package com.miladro.simplerest.api.dto.Post;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreatePostDTO {
    //TODO: add validation
    // TODO: this field could be retrieved from security context, later. for now it will be received from client.
//    private String userId;
    @NotEmpty
    private String title;
    private String body;
}
