package com.mycompany.entitybase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityString implements BaseEntity<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
    this.id = s;
    }
}
