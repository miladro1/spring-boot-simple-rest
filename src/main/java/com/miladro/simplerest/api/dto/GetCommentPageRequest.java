package com.miladro.simplerest.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
@Schema
public class GetCommentPageRequest extends CustomPageRequest implements Cloneable {
    @Schema(example = "'example@example.com")
    private String email;

    @Schema(example = "10")
    private String postId;
}
