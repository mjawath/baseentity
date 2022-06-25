/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author LENOVO PC
 */
@MappedSuperclass
public interface BaseEntity<ID> extends Serializable {


    ID getId();

    void setId(ID id);
}
