package com.miladro.simplerest.seeder.Comment;

import com.miladro.simplerest.aspect.ErrorHandlingControllerAdvice;
import com.miladro.simplerest.dal.entity.Comment;
import com.miladro.simplerest.dal.repository.comment.CommentRepository;
import com.miladro.simplerest.seeder.Comment.remote.CommentHttpDataSourceClient;
import com.miladro.simplerest.seeder.Comment.remote.CommentResponseModel;
import com.miladro.simplerest.seeder.Seeder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsSeeder implements Seeder {
    private final CommentRepository commentRepository;
    private final CommentHttpDataSourceClient commentHttpDataSourceClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

    public CommentsSeeder(CommentRepository commentRepository, CommentHttpDataSourceClient commentHttpDataSourceClient) {
        this.commentRepository = commentRepository;
        this.commentHttpDataSourceClient = commentHttpDataSourceClient;
    }

    @Override
    public void seed() {
        try {
//            CommentListResponse commentListResponse = commentDataSourceClient.getCommentList();
            List<CommentResponseModel> commentResponseModelList = commentHttpDataSourceClient.getCommentList();
            commentResponseModelList
//                    .getCommentList()
                    .forEach(commentResponseModel -> {
                if (!commentRepository.existsById(commentResponseModel.getId()))
                    saveComment(commentResponseModel);
            });
        } catch (Exception e) {
            LOGGER.error("Comment Seeding Failed", e);
        }
    }

    private void saveComment(CommentResponseModel commentResponseModel) {
        Comment comment = new Comment();
        comment.setId(commentResponseModel.getId());
        comment.setPostId(commentResponseModel.getPostId());
        comment.setName(commentResponseModel.getName());
        comment.setEmail(commentResponseModel.getEmail());
        comment.setBody(commentResponseModel.getBody());
        commentRepository.save(comment);
    }
}
