package com.mycompany.entitybase.model;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SortElement
 */
@Validated
public class SortElement {
    private String attribute = null;

    private boolean asc;

    public SortElement attribute(String attribute) {
        this.attribute = attribute;
        return this;
    }


    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public SortElement asc(Boolean asc) {
        this.asc = asc;
        return this;
    }


    public Boolean isAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortElement sortElement = (SortElement) o;
        return Objects.equals(this.attribute, sortElement.attribute) &&
                Objects.equals(this.asc, sortElement.asc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, asc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SortElement {\n");

        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    asc: ").append(toIndentedString(asc)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

