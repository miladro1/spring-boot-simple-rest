package com.miladro.simplerest.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Entity implements Cloneable {
    @Id
    private Long id;

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
