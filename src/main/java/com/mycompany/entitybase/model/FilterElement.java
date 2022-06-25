package com.mycompany.entitybase.model;

/**
 * FilterElement
 */

public class FilterElement {
    private String attribute;
    private String value;
    private String value2;
    private String ops;
    private String orOrAnd;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

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

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public String getOrOrAnd() {
        return orOrAnd;
    }

    public void setOrOrAnd(String orOrAnd) {
        this.orOrAnd = orOrAnd;
    }
}

