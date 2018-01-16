/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author LENOVO PC
 */
public interface BaseDAO<T, ID extends Serializable>  extends JpaRepository<T, ID> {


//    List<T> findAll();

    List<T> goToPage(int pageNo);

    List<T> goToPage(int pageNo,int size);
//
//    <S extends T> S save(S var1);

//    @Query("select c from #{#entityName} c where c.code = ?1 ")//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
//     List<T> searchEq(String column,Object value);

     List<T> search(String column,Object value);
     
     
    public Page<T> searchPageable(String column,Object value,Pageable pageable);
    
    public Page<T> searchPageable(String column,Object value,Object value2,Pageable pageable);

    //    @Query("select c from #{#entityName} c where c.code like ?1 ")//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
//    List<T> searchLike(String column,String value);
//    
//     public T findOne(ID id);
//
//    public T getOne(ID id) ;
//
////    public <S extends T> S save(S obj);
//
//        void delete(T obj);
//
//        void delete(ID id);

//    public T getByKey(ID key) ;
//
//    public void persist(T entity);
//    
//    public void update(T entity);
//    
//    public void remove(T entity);

}