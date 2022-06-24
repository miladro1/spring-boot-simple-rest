package com.miladro.simplerest.api.mapper;

import com.miladro.simplerest.api.dto.Comment.CommentDTO;
import com.miladro.simplerest.api.dto.Comment.CreateCommentDTO;
import com.miladro.simplerest.api.dto.Comment.UpdateCommentDTO;
import com.miladro.simplerest.dal.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO getCommentDTO(Comment comment);

    Comment getEntity(CreateCommentDTO createCommentDTO);
    Comment getEntity(UpdateCommentDTO updateCommentDTO);
}
