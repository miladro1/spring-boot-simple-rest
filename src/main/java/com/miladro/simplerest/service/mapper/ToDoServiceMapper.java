package com.miladro.simplerest.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ToDoServiceMapper {
    ToDoServiceMapper INSTANCE = Mappers.getMapper(ToDoServiceMapper.class);
}
