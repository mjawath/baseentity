/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase.service;

import com.mycompany.entitybase.BaseEntity;
import com.mycompany.entitybase.dao.BaseDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author LENOVO PC
 */
public interface IService<T extends BaseEntity>  {

    List<T> findAll();



    < ID extends Serializable> T findById(ID id);

    
    void setDao(BaseDAO dao);

    <S extends T> S save(S object);

    List<T> search(String column,Object value);

    List<T> goToPage(int pageNo);

    Page<T> searchPageable(String column,Object value,Pageable pageable);

    List<T> search(String column,Object value,Object value2);
    
    Page<T> searchPageable(String column,Object value,Object value2,Pageable pageable);
    
    T create(T object);

    T update(T object);

    T patch(T objectTopatch) ;

    < ID extends Serializable> void  deleteById(ID objId);

    void delete(T obj);
}
