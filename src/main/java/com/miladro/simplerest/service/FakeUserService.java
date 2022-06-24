package com.miladro.simplerest.service;

import org.springframework.stereotype.Service;

@Service
public class FakeUserService implements UserService{
    @Override
    public Long getAuthenticatedUserId() {
        return 1L;
    }
}
