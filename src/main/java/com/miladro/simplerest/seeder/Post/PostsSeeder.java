package com.miladro.simplerest.seeder.Post;

import com.miladro.simplerest.aspect.ErrorHandlingControllerAdvice;
import com.miladro.simplerest.dal.entity.Post;
import com.miladro.simplerest.dal.repository.post.PostRepository;
import com.miladro.simplerest.seeder.Post.remote.PostHttpDataSourceClient;
import com.miladro.simplerest.seeder.Post.remote.PostResponseModel;
import com.miladro.simplerest.seeder.Seeder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostsSeeder implements Seeder {
    private final PostRepository postRepository;
    private final PostHttpDataSourceClient client;
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

    public PostsSeeder(PostRepository PostRepository, PostHttpDataSourceClient client) {
        this.postRepository = PostRepository;
        this.client = client;
    }

    @Override
    public void seed() {
        try {
//            PostListResponse postListResponse = postDataSourceClient.getPostList();
            List<PostResponseModel> postListResponse = client.getPostList();
            postListResponse
//                    .getPostList()
                    .forEach(postResponseModel -> {
                if (!postRepository.existsById(postResponseModel.getId()))
                    savePost(postResponseModel);
            });
        } catch (Exception e) {
            LOGGER.error("Post Seeding Exception", e);
        }
    }

    private void savePost(PostResponseModel postResponseModel) {
        Post post = new Post();
        post.setId(postResponseModel.getId());
        post.setUserId(postResponseModel.getUserId());
        post.setTitle(postResponseModel.getTitle());
        post.setBody(postResponseModel.getBody());
        postRepository.save(post);
    }
}
