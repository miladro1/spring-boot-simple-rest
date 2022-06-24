package com.miladro.simplerest.config.feign;

public class FeignGeneralException extends RuntimeException {
    private Integer status;
    private String body;

    public FeignGeneralException(Integer status) {
        this.status = status;
    }
    public FeignGeneralException(Integer status , String message) {
        this.body = message;
        this.status = status;
    }

    public FeignGeneralException(Throwable cause) {
        super(cause);
    }

    public Integer getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }
}
