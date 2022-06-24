package com.miladro.simplerest.service.Counter;

import com.miladro.simplerest.dal.entity.Counter;

public interface CounterService<T> {
    Long incrId(T entity);
    Counter initCounter();
    Counter incrCounter();
}
