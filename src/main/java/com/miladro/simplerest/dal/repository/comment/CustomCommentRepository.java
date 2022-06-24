package com.miladro.simplerest.dal.repository.comment;

import com.miladro.simplerest.dal.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomCommentRepository {
    Page<Comment> findAllPaginated(String email, Long postId, Pageable pageable);
}
