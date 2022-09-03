package com.mycompany.entitybase.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SearchResult<T> extends PageImpl<T> {
    public SearchResult(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

}
