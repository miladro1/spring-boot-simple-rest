package com.miladro.simplerest.seeder.ToDo;

import com.miladro.simplerest.aspect.ErrorHandlingControllerAdvice;
import com.miladro.simplerest.dal.entity.ToDo;
import com.miladro.simplerest.dal.repository.todo.ToDoRepository;
import com.miladro.simplerest.seeder.ToDo.remote.ToDoHttpDataSourceClient;
import com.miladro.simplerest.seeder.ToDo.remote.ToDoResponseModel;
import com.miladro.simplerest.seeder.Seeder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDosSeeder implements Seeder {
    private final ToDoRepository toDoRepository;
    private final ToDoHttpDataSourceClient httpClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

    public ToDosSeeder(ToDoRepository ToDoRepository, ToDoHttpDataSourceClient httpClient) {
        this.toDoRepository = ToDoRepository;
        this.httpClient = httpClient;
    }

    @Override
    public void seed() {
        try {
            List<ToDoResponseModel> toDoListResponse = httpClient.getToDoList();
            toDoListResponse.forEach(toDoResponseModel -> {
                if (!toDoRepository.existsById(toDoResponseModel.getId()))
                    saveToDo(toDoResponseModel);
            });
        } catch (Exception e) {
            LOGGER.error("ToDo Seeding Exception", e);
        }
    }

    private void saveToDo(ToDoResponseModel toDoResponseModel) {
        ToDo post = new ToDo();
        post.setId(toDoResponseModel.getId());
        post.setUserId(toDoResponseModel.getUserId());
        post.setTitle(toDoResponseModel.getTitle());
        post.setCompleted(toDoResponseModel.getCompleted());
        toDoRepository.save(post);
    }
}
