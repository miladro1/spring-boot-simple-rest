package com.miladro.simplerest.api.controller;

import com.miladro.simplerest.api.dto.Comment.CommentDTO;
import com.miladro.simplerest.api.dto.Comment.CreateCommentDTO;
import com.miladro.simplerest.api.dto.Comment.UpdateCommentDTO;
import com.miladro.simplerest.api.mapper.CommentMapper;
import com.miladro.simplerest.utils.PaginationUtil;
import com.miladro.simplerest.api.dto.GetCommentPageRequest;
import com.miladro.simplerest.dal.entity.Comment;
import com.miladro.simplerest.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "comments controller")
public class CommentsController {

    private final CommentService commentService;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   @Operation(summary = "create comment")
    public CommentDTO createComment(@RequestBody @Validated CreateCommentDTO createCommentDTO) {
        Comment createdComment = commentService.createComment(commentMapper.getEntity(createCommentDTO));
        return commentMapper.getCommentDTO(createdComment);
    }


    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
   @Operation(summary = "update comment")
    public void updateComment(
            @RequestBody UpdateCommentDTO updateCommentDTO,
            @PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id)
    {
        Comment updatedComment = commentService.updateComment(id, commentMapper.getEntity(updateCommentDTO));
        commentMapper.getCommentDTO(updatedComment);
    }

    @GetMapping("/{id}")
    @ResponseBody
   @Operation(summary = "show a comment")
    public CommentDTO getCommentById(@PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id) {
        return commentMapper.getCommentDTO(commentService.getCommentById(id));
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "get comments paginated")
    public Page<CommentDTO> getCommentPage(@Validated GetCommentPageRequest getCommentPageRequest) {
        return commentService.listCommentsPaginated(
                getCommentPageRequest.getEmail(),
                getCommentPageRequest.getPostId(),
                PaginationUtil.getPageableFromPageRequest(getCommentPageRequest)
        ).map(commentMapper::getCommentDTO);
    }

//    @GetMapping
//    @ResponseBody
//    @Operation(summary = "List of the comments by postId")
//    public List<CommentDTO> getPostCommentsList(
//            @Nullable @RequestParam(value = "postId", required = false) String postId
//    ) {
//        return commentService.getAllCommentsByPostId(postId).stream()
//                .map(commentMapper::getCommentDTO)
//                .collect(Collectors.toList());
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
   @Operation(summary = "delete comment")
    public boolean deleteComment(@PathVariable("id") @Parameter(name = "id", example = "'12345'", required = true) String id) {
        return commentService.deleteComment(id);
    }
}

