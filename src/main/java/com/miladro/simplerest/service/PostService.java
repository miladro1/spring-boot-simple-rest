package com.miladro.simplerest.service;

import com.miladro.simplerest.dal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post createPost(Post post);
    Post updatePost(String postId, Post post);
    Post GetPostById(String id);
//    List<Post> GetAllPostsByTitleContains(String title);
//    List<Post> listAllPosts();
    Page<Post> listPostsPaginated(String title, String id, String userId, Pageable pageable);
    Boolean deletePost(String id);
//    Boolean deletePostsBulk(List<Long> idList);
}
