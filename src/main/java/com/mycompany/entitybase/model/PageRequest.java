package com.mycompany.entitybase.model;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class PageRequest implements Pageable {
    private int size;
    private String offSetAttribute;
    private String offSetValue;
    private String offSetType;
    private boolean isOffSetAsc;
    private int page;

    public PageRequest(int page, int size) {
        this.page=page;
        this.size=size;
    }


    @Override
    public boolean isPaged() {
        return true;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    public Sort getSort() {
        return null;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }


    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }


    public Pageable previous() {
        return null;
    }

    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Pageable.super.toOptional();
    }


    public Pageable withPage(int pageNumber) {
        return null;
    }

    public String getOffSetAttribute() {
        return offSetAttribute;
    }

    public void setOffSetAttribute(String offSetAttribute) {
        this.offSetAttribute = offSetAttribute;
    }

    public String getOffSetValue() {
        return offSetValue;
    }

    public void setOffSetValue(String offSetValue) {
        this.offSetValue = offSetValue;
    }

    public String getOffSetType() {
        return offSetType;
    }

    public void setOffSetType(String offSetType) {
        this.offSetType = offSetType;
    }

    public boolean isOffSetAsc() {
        return isOffSetAsc;
    }

    public void setOffSetAsc(boolean offSetAsc) {
        isOffSetAsc = offSetAsc;
    }
}
