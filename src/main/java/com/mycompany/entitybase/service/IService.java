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

import com.mycompany.entitybase.model.SearchRequest;
import com.mycompany.entitybase.model.SearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author LENOVO PC
 */
public interface IService<T extends BaseEntity>  {

    List<T> findAll();



    T findById(String id);

    
    void setDao(BaseDAO dao);

    <S extends T> S save(S object);

    List<T> search(String column,Object value);

    default SearchResult<T> search(SearchRequest request){
        return new SearchResult<>(null,null,0);
    }

    List<T> goToPage(int pageNo);

    Page<T> searchPageable(String column,Object value,Pageable pageable);

    List<T> search(String column,Object value,Object value2);
    
    Page<T> searchPageable(String column,Object value,Object value2,Pageable pageable);
    
    T create(T object);

    T update(T object);

    T patch(T objectTopatch) ;

    void  deleteById(String objId);

    void delete(T obj);
    
    void validateEntity(T object) ;
}
