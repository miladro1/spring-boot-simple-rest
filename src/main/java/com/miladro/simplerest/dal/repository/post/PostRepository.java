package com.miladro.simplerest.dal.repository.post;

import com.miladro.simplerest.dal.entity.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>, CustomPostRepository {
    List<Post> findAll();
    List<Post> findAllById(List<Long> idList);
    List<Post> findAllByTitleLike(String title);
}
