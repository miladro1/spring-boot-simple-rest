package com.miladro.simplerest.service.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CommonException {
    public NotFoundException()
    {
        super(HttpStatus.NOT_FOUND,"{common.error.notfound}");
    }
    public NotFoundException(Throwable cause) {
        super(HttpStatus.NOT_FOUND,"{common.error.notfound}",cause);
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
