package com.miladro.simplerest.dal.repository.todo;

import com.miladro.simplerest.dal.entity.ToDo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, Long> {
    List<ToDo> findAll();
    List<ToDo> findAllByUserIdAndCompleted(Long userId, Boolean completed);
}
