package com.mycompany.entitybase.model;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchRequest
 */

public class SearchRequest {
    private String column;
    private String value;
    private String value2;

    public Class getPersistenceClass() {
        return persistenceClass;
    }

    public void setPersistenceClass(Class persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    private Class persistenceClass;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

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

