package com.miladro.simplerest.service;

import com.miladro.simplerest.dal.entity.Post;
import com.miladro.simplerest.dal.repository.post.PostRepository;
import com.miladro.simplerest.service.Counter.PostsCounterService;
import com.miladro.simplerest.service.exception.NotFoundException;
import com.miladro.simplerest.service.mapper.PostServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SimplePostService implements PostService{
    private final PostRepository repository;
    private final PostsCounterService counterService;
    private final UserService userService;
    private final PostServiceMapper mapper = PostServiceMapper.INSTANCE;

    public SimplePostService(PostRepository postRepository, PostsCounterService counterService, UserService userService) {
        this.repository = postRepository;
        this.counterService = counterService;
        this.userService = userService;
    }

    @Override
    public Post createPost(Post post) {
        counterService.incrId(post);
        post.setUserId(userService.getAuthenticatedUserId());
        return repository.save(post);
    }

    @Override
    public Post updatePost(String id, Post newPostData) {
        Post post = repository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
        mapper.updatePostEntity(newPostData, post);
        return repository.save(post);
    }

    @Override
    public Post GetPostById(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
    }

//    @Override
//    public List<Post> GetAllPostsByTitleContains(String title) {
//        return repository.findAllByTitleLike(title);
//    }
//
//    @Override
//    public List<Post> listAllPosts() {
//        return repository.findAll();
//    }

    @Override
    public Page<Post> listPostsPaginated(String title, String id, String userId, Pageable pageable) {
        return repository.findAllPaginated(
                title,
                Objects.nonNull(id) ? Long.parseLong(id) : null,
                Objects.nonNull(userId) ? Long.parseLong(userId) : null,
                pageable
        );
    }

    @Override
    public Boolean deletePost(String id) {
        Post post = repository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
        repository.delete(post);
        return true;
    }

//    @Override
//    public Boolean deletePostsBulk(List<Long> idList) {
//        List<Post> postList = repository.findAllById(idList);
//        repository.deleteAll(postList);
//        return true;
//    }
}
