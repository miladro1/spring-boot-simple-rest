package com.miladro.simplerest.service.mapper;

import com.miladro.simplerest.dal.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentServiceMapper {
    CommentServiceMapper INSTANCE = Mappers.getMapper(CommentServiceMapper.class);

    @Mapping(target = "prevComment.id", ignore = true)
    void updateCommentEntity(Comment newData, @MappingTarget Comment prevComment);
}
