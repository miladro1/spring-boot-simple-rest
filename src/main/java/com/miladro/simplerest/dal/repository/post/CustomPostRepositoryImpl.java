package com.miladro.simplerest.dal.repository.post;

import com.miladro.simplerest.dal.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

public class CustomPostRepositoryImpl implements CustomPostRepository {
    private final MongoTemplate mongoTemplate;

    public CustomPostRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Post> findAllPaginated(String title, Long id, Long userId, Pageable pageable) {
        Query query = new Query().with(pageable);
        if (Objects.nonNull(title))
            query.addCriteria(Criteria.where("title").regex(".*" + title + ".*"));

        if (Objects.nonNull(id))
            query.addCriteria(Criteria.where("id").is(id));

        if (Objects.nonNull(userId))
            query.addCriteria(Criteria.where("userId").is(userId));

        List<Post> result = mongoTemplate.find(query, Post.class);

        return PageableExecutionUtils.getPage(
                result,
                pageable,
                () -> mongoTemplate.count(query.limit(-1).skip(-1), Post.class));
    }
}
