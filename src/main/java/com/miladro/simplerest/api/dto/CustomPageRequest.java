package com.miladro.simplerest.api.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Set;

@Data
@Schema
public class CustomPageRequest {

    @Schema(example = "1", description = "start from 0")
    @Positive
    @NotNull(message = "{general.notEmpty}")
    protected Integer pageNumber;

    @Schema(example = "10", description = "must be greater than 0")
    @Positive
    @Min(value = 0)
    @NotNull(message = "{general.notEmpty}")
    protected Integer pageSize;

    @Schema(example = "ASC", description = "ASC=ascending ,DESC=descending")
    private Sort sort;

    @Schema(example = "[key1,key2]", description = "sort will be based on these words")
    private Set<String> sortKey;

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber.compareTo(0) <= 0 ? 0 : pageNumber;
    }

}
