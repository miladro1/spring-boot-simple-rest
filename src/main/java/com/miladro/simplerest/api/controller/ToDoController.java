package com.miladro.simplerest.api.controller;

import com.miladro.simplerest.api.dto.ToDo.ToDoDTO;
import com.miladro.simplerest.api.mapper.ToDoMapper;
import com.miladro.simplerest.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/todos")
@Tag(name = "todos api", description = "todos controller")
public class ToDoController {

    private final ToDoService toDoService;
    private final ToDoMapper toDoMapper = ToDoMapper.INSTANCE;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "get toDos paginated")
    public List<ToDoDTO> getAllTodos() {
        return toDoService.listAllToDos().stream().map(toDoMapper::getDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"userId", "completed"})
    @ResponseBody
    @Operation(summary = "get toDos paginated")
    public List<ToDoDTO> getAllTodosByUserIdAndCompleted(
            @RequestParam("userId") String userId,
            @RequestParam("completed") Boolean completed
    ) {
        return toDoService.listAllToDos(userId, completed).stream().map(toDoMapper::getDTO)
                .collect(Collectors.toList());
    }
}

