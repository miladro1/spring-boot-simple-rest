package com.miladro.simplerest.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
@Schema
public class GetPostPageRequest extends CustomPageRequest implements Cloneable {
    @Schema(example = "'lurem ipsom")
    private String title;

    @Schema(example = "10")
    private String id;

    @Schema(example = "5")
    private String userId;
}
