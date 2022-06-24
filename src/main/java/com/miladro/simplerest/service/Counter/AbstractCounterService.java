package com.miladro.simplerest.service.Counter;

import com.miladro.simplerest.dal.entity.Counter;
import com.miladro.simplerest.dal.entity.Entity;
import com.miladro.simplerest.dal.repository.CounterRepository;

import java.util.List;

public abstract class AbstractCounterService<E extends Entity>
        implements CounterService<E>
{
    protected CounterRepository repository;

    protected AbstractCounterService(CounterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long incrId(E entity) {
        List<Counter> counterList = (List<Counter>) repository.findAll();
        Counter counter = counterList.isEmpty() ? initCounter() : incrCounter();
        entity.setId(counter.getCount());
        return counter.getCount();
    }
}
