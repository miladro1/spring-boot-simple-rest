package com.miladro.simplerest.service.mapper;

import com.miladro.simplerest.dal.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostServiceMapper {
    PostServiceMapper INSTANCE = Mappers.getMapper(PostServiceMapper.class);

    @Mapping(target = "prevPost.id", ignore = true)
    void updatePostEntity(Post newPostData, @MappingTarget Post prevPost);
}
