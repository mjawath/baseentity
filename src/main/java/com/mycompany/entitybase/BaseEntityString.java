package com.mycompany.entitybase;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public class BaseEntityString implements BaseEntity<String> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
    this.id = s;
    }
}
