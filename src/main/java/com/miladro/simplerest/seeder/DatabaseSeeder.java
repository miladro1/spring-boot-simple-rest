package com.miladro.simplerest.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final List<Seeder> seederList;

    public DatabaseSeeder(List<Seeder> seederList) {
        this.seederList = seederList;
    }

    @EventListener
    @Async("seederTaskExecutor")
    public void seed(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.info("************ " + DatabaseSeeder.class.getName() + " is running ************");
        seederList.forEach(Seeder::seed);
    }
}
