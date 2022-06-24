package com.miladro.simplerest.dal.repository.comment;

import com.miladro.simplerest.dal.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, CustomCommentRepository {
    List<Comment> findAll();
    List<Comment> findAllByPostId(Long postId);
}
