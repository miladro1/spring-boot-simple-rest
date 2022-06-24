package com.miladro.simplerest.api.mapper;

import com.miladro.simplerest.api.dto.Post.CreatePostDTO;
import com.miladro.simplerest.api.dto.Post.PostDTO;
import com.miladro.simplerest.api.dto.Post.UpdatePostDTO;
import com.miladro.simplerest.dal.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post getEntity(CreatePostDTO createPostDTO);
    Post getEntity(UpdatePostDTO updatePostDTO);

    PostDTO getPostDTO(Post post);
}
