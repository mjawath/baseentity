/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author LENOVO PC
 */
@NoRepositoryBean
public interface SFDAO<T extends Object, ID extends Serializable> extends CrudRepository<T, ID>,BaseDAO<T, ID> {

    List<T> search(String column, Object value);

//    @Query("select c from #{#entityName} c where ?1 = ?2")//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
//    List<T> searchEq(String column,Object value);
//
//    @Query("select c from #{#entityName} c where ?1 like ?2")//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
//    List<T> searchLike(String column,String value);

//    default List<T> search(String column,Object value){
//        if(!column.contains("c.")){
//            column ="c."+column; //column should contain c. or else we should add it
//        }
//        if(value !=null && value.toString().contains("%")){
//            return searchLike(column,value.toString());
//        }
//        return searchEq(column,value);
//    }
}
