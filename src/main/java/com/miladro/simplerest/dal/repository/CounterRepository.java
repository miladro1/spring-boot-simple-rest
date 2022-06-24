package com.miladro.simplerest.dal.repository;

import com.miladro.simplerest.dal.entity.Counter;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CounterRepository extends PagingAndSortingRepository<Counter, String> {
    List<Counter> findAll();
}
