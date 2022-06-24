package com.miladro.simplerest.seeder.ToDo.remote;

import com.miladro.simplerest.config.feign.FeignResponseConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@FeignClient(name = "todos-data-source-client", url = "${remote.datasource.todos}", configuration = FeignResponseConfiguration.class)
public interface ToDoHttpDataSourceClient {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<ToDoResponseModel> getToDoList();
//    PostListResponse getToDoList();
}
