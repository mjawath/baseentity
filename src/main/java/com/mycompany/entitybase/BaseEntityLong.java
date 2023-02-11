package com.mycompany.entitybase;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class BaseEntityLong implements BaseEntity<Long> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Long id;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long s) {
    this.id = s;
    }
}
