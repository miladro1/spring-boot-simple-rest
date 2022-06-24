package com.miladro.simplerest.dal.repository.comment;

import com.miladro.simplerest.dal.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;

public class CustomCommentRepositoryImpl implements CustomCommentRepository {
    private final MongoTemplate mongoTemplate;

    public CustomCommentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Comment> findAllPaginated(String email, Long postId, Pageable pageable) {
        Query query = new Query().with(pageable);
        if (Objects.nonNull(email))
            query.addCriteria(Criteria.where("email").is(email));

        if (Objects.nonNull(postId))
            query.addCriteria(Criteria.where("postId").is(postId));

        List<Comment> result = mongoTemplate.find(query, Comment.class);

        return PageableExecutionUtils.getPage(
                result,
                pageable,
                () -> mongoTemplate.count(query.limit(-1).skip(-1), Comment.class));
    }
}
