package com.miladro.simplerest.api.dto.ToDo;

import lombok.Data;

@Data
public class ToDoDTO {
    //TODO: change long ids to String
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;
}
