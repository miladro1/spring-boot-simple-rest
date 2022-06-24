package com.miladro.simplerest.service;

import com.miladro.simplerest.dal.entity.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> listAllToDos();
    List<ToDo> listAllToDos(String userId, Boolean completed);
}
