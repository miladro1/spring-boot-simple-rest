package com.miladro.simplerest.dal.repository.post;

import com.miladro.simplerest.dal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomPostRepository {
    Page<Post> findAllPaginated(String title, Long id, Long userId, Pageable pageable);
}
