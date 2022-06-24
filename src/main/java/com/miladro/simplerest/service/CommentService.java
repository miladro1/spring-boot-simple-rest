package com.miladro.simplerest.service;

import com.miladro.simplerest.dal.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    Comment updateComment(String id, Comment comment);
    Comment getCommentById(String id);
    List<Comment> getAllComments();
    List<Comment> getAllCommentsByPostId(String postId);
    Page<Comment> listCommentsPaginated(String email, String postId, Pageable pageable);
    Boolean deleteComment(String id);

}
