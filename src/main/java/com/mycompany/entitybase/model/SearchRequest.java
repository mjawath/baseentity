package com.mycompany.entitybase.model;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchRequest
 */

public class SearchRequest {
    private List<FilterElement> filterBy;

    private List<SortElement> sortBy;

    private PageRequest pageable;

    public SearchRequest addFilterByItem(FilterElement filterByItem) {
        if (this.filterBy == null) {
            this.filterBy = new ArrayList<FilterElement>();
        }
        this.filterBy.add(filterByItem);
        return this;
    }


    public SearchRequest addSortByItem(SortElement sortByItem) {
        if (this.sortBy == null) {
            this.sortBy = new ArrayList<SortElement>();
        }
        this.sortBy.add(sortByItem);
        return this;
    }

    public List<FilterElement> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(List<FilterElement> filterBy) {
        this.filterBy = filterBy;
    }

    public List<SortElement> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<SortElement> sortBy) {
        this.sortBy = sortBy;
    }

    public PageRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageRequest pageable) {
        this.pageable = pageable;
    }
}

