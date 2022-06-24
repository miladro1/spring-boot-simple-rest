package com.miladro.simplerest.service.Counter;

import com.miladro.simplerest.dal.entity.Comment;
import com.miladro.simplerest.dal.entity.Counter;
import com.miladro.simplerest.dal.repository.CounterRepository;
import com.miladro.simplerest.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentsCounterService extends AbstractCounterService<Comment> {

    public CommentsCounterService(CounterRepository counterRepository) {
        super(counterRepository);
    }

    @Override
    public Counter initCounter() {
        Counter counter = new Counter();
        counter.setPostCounter(1L);
        counter.setCount(1L);
        repository.save(counter);
        return counter;
    }

    @Override
    public Counter incrCounter() {
        Counter counter = repository.findAll().stream().findFirst()
                .orElseThrow(NotFoundException::new);
        Long newCount = counter.getCommentsCounter() + 1;
        counter.setCommentsCounter(newCount);
        counter.setCount(newCount);
        return repository.save(counter);
    }
}
