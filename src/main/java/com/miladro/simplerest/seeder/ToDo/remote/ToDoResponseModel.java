package com.miladro.simplerest.seeder.ToDo.remote;

import lombok.Data;

@Data
public class ToDoResponseModel {
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;
}
