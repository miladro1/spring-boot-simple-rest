package com.miladro.simplerest.utils;

import com.miladro.simplerest.api.dto.CustomPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PaginationUtil {
    public static Pageable getPageableFromPageRequest(CustomPageRequest pageRequest){
        String[] queryParams = (Objects.isNull(pageRequest.getSortKey())
                || (pageRequest.getSortKey().size() == 0)) ? null : pageRequest.getSortKey().toArray(new String[0]);

        String sortBy = Objects.isNull(pageRequest.getSort())? null : pageRequest.getSort().name();

        if (Objects.isNull(pageRequest.getPageNumber()))
            pageRequest.setPageNumber(0);

        if (Objects.isNull(pageRequest.getPageSize()))
            pageRequest.setPageSize(10);

        if (Objects.nonNull(queryParams) && Objects.nonNull(sortBy))
            return PageRequest.of(
                    pageRequest.getPageNumber(),
                    pageRequest.getPageSize(),
                    Sort.Direction.fromString(sortBy),
                    queryParams
            );

        return PageRequest.of(
                pageRequest.getPageNumber(),
                pageRequest.getPageSize()
        );

    }
}
