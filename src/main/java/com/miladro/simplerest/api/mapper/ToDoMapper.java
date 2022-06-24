package com.miladro.simplerest.api.mapper;

import com.miladro.simplerest.api.dto.ToDo.ToDoDTO;
import com.miladro.simplerest.dal.entity.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ToDoMapper {
    ToDoMapper INSTANCE = Mappers.getMapper(ToDoMapper.class);

    ToDoDTO getDTO(ToDo todo);
}
