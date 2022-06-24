package com.miladro.simplerest.api.dto.ToDo;

import lombok.Data;

@Data
public class CreateToDoDTO {
    //TODO: add validation
    private String title;
    private Boolean completed;
}
