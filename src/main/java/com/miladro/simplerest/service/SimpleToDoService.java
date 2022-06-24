package com.miladro.simplerest.service;

import com.miladro.simplerest.dal.entity.ToDo;
import com.miladro.simplerest.dal.repository.todo.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleToDoService implements ToDoService {
    private final ToDoRepository repository;

    public SimpleToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ToDo> listAllToDos() {
        return repository.findAll();
    }

    @Override
    public List<ToDo> listAllToDos(String userId, Boolean completed) {
        return repository.findAllByUserIdAndCompleted(Long.parseLong(userId), completed);
    }
}
