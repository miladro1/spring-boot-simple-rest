package com.miladro.simplerest.service.Counter;

import com.miladro.simplerest.dal.entity.Counter;
import com.miladro.simplerest.dal.entity.Post;
import com.miladro.simplerest.dal.repository.CounterRepository;
import com.miladro.simplerest.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostsCounterService extends AbstractCounterService<Post> {
    private final CounterRepository counterRepository;

    public PostsCounterService(CounterRepository counterRepository) {
        super(counterRepository);
        this.counterRepository = counterRepository;
    }

    @Override
    public Counter initCounter() {
        Counter counter = new Counter();
        counter.setPostCounter(1L);
        counter.setCount(1L);
        counterRepository.save(counter);
        return counter;
    }

    @Override
    public Counter incrCounter() {
        Counter counter = counterRepository.findAll().stream().findFirst()
                .orElseThrow(NotFoundException::new);
        Long newCount = counter.getPostCounter() + 1;
        counter.setPostCounter(newCount);
        counter.setCount(newCount);
        return counterRepository.save(counter);
    }
}
