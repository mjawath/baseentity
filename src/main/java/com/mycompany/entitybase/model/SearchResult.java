package com.mycompany.entitybase.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SearchResult<T> extends PageImpl<T> {
    private long count;// total result count is wrong
    public SearchResult(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.count=total;
    }

    public long getCount() {
        return count;
    }
}
