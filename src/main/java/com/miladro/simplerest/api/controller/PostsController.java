package com.miladro.simplerest.api.controller;

import com.miladro.simplerest.api.dto.Comment.CommentDTO;
import com.miladro.simplerest.api.dto.Post.CreatePostDTO;
import com.miladro.simplerest.api.dto.Post.PostDTO;
import com.miladro.simplerest.api.dto.Post.UpdatePostDTO;
import com.miladro.simplerest.api.mapper.CommentMapper;
import com.miladro.simplerest.api.mapper.PostMapper;
import com.miladro.simplerest.api.dto.GetPostPageRequest;
import com.miladro.simplerest.utils.PaginationUtil;
import com.miladro.simplerest.dal.entity.Post;
import com.miladro.simplerest.service.CommentService;
import com.miladro.simplerest.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "posts controller")
public class PostsController {
    private final PostService postService;
    private final CommentService commentService;
    private final PostMapper postMapper = PostMapper.INSTANCE;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    public PostsController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create post")
    public PostDTO createPost(@RequestBody @Validated CreatePostDTO createPostDTO) {
        Post createdPost = postService.createPost(postMapper.getEntity(createPostDTO));
        return postMapper.getPostDTO(createdPost);
    }


    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
   @Operation(summary = "update post")
    public void updatePost(
            @RequestBody UpdatePostDTO updatePostDTO,
            @PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id)
    {
        Post updatedPost = postService.updatePost(id, postMapper.getEntity(updatePostDTO));
        postMapper.getPostDTO(updatedPost);
    }

    @GetMapping("/{id}")
    @ResponseBody
   @Operation(summary = "show a post")
    public PostDTO getPost(@PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id) {
        return postMapper.getPostDTO(postService.GetPostById(id));
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "get post page")
    public Page<PostDTO> getPostPage(@Validated GetPostPageRequest getPostPageRequest) {
        return postService.listPostsPaginated(
                getPostPageRequest.getTitle(),
                getPostPageRequest.getId(),
                getPostPageRequest.getUserId(),
                PaginationUtil.getPageableFromPageRequest(getPostPageRequest)
        ).map(postMapper::getPostDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
   @Operation(summary = "delete post")
    public boolean deletePost(@PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id) {
        return postService.deletePost(id);
    }

    @GetMapping("/{id}/comments")
    @ResponseBody
   @Operation(summary = "List of comments of the post")
    public List<CommentDTO> getPostCommentsList(
            @PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id
    )
    {
        return commentService.getAllCommentsByPostId(id).stream()
                .map(commentMapper::getCommentDTO)
                .collect(Collectors.toList());
    }
}

