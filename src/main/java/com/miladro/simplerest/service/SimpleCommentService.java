package com.miladro.simplerest.service;

import com.miladro.simplerest.dal.entity.Comment;
import com.miladro.simplerest.dal.repository.comment.CommentRepository;
import com.miladro.simplerest.service.Counter.CommentsCounterService;
import com.miladro.simplerest.service.exception.NotFoundException;
import com.miladro.simplerest.service.mapper.CommentServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SimpleCommentService implements CommentService {
    private final CommentRepository repository;
    private final CommentsCounterService counterService;
    private final CommentServiceMapper mapper = CommentServiceMapper.INSTANCE;

    public SimpleCommentService(CommentRepository repository, CommentsCounterService counterService) {
        this.repository = repository;
        this.counterService = counterService;
    }

    @Override
    public Comment createComment(Comment comment) {
        counterService.incrId(comment);
        return repository.save(comment);
    }

    @Override
    public Comment updateComment(String id, Comment comment) {
        Comment existingComment = repository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
        mapper.updateCommentEntity(comment, existingComment);
        return repository.save(existingComment);
    }

    @Override
    public Comment getCommentById(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    @Override
    public List<Comment> getAllCommentsByPostId(String postId) {
        return Objects.nonNull(postId) ? repository.findAllByPostId(Long.parseLong(postId)) : getAllComments();
    }

    @Override
    public Page<Comment> listCommentsPaginated(String email, String postId, Pageable pageable) {
        Page<Comment> comments = repository.findAllPaginated(email, Long.parseLong(postId), pageable);
        return repository.findAllPaginated(email, Long.parseLong(postId), pageable);
    }

    @Override
    public Boolean deleteComment(String id) {
        Comment comment = repository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
        repository.delete(comment);
        return true;
    }
}
