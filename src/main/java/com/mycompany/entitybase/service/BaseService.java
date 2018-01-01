/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase.service;

import com.mycompany.entitybase.BaseEntity;
import com.mycompany.entitybase.DataException;
import com.mycompany.entitybase.dao.BaseDAO;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author LENOVO PC
 */
public class BaseService<T extends BaseEntity> implements IService<T> {

    protected BaseDAO<T, String> dao;// id parameter should be gererics id extents serialisable 

    public BaseService() {
        System.out.println("---------initialising @ BaseService----" + getClass().getSimpleName());
    }

    public BaseService(BaseDAO<T,String> dao) {
        this.dao = dao;
    }

    public List<T> findAll() {
        return dao.findAll();
//       System.out.println("whielf running super level service method change test");    
//    return null;
    }

    public List<T> search(String column, Object value) {
        return dao.search(column, value);
    }

    public List<T> goToPage(int pageNo){
        List<T> list = goToPage(pageNo,100);
        return list;
    }

    public List<T> goToPage(int pageNo,int size){
        List<T> list =dao.goToPage(pageNo,size);
        return list;
    }

    public Page<T> searchPageable(String column, Object value, Pageable pageable) {
        Page<T> page= dao.searchPageable(column, value, pageable);
        return page;
    }

    public List<T> search(String column, Object value, Object value2) {
        return null;
    }

    public Page<T> searchPageable(String column, Object value, Object value2, Pageable pageable) {
        return null;
    }


    public T findById(Serializable s){    
        return  dao.findOne((String)s);
    }


    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }

    public <S extends T> S save(S object) {
        try {
            return dao.save(object);
        } catch (Exception e) {
            throw new DataException("persistance error", e);
        }
    }


    public T create(T object){


        if(object instanceof BaseEntity && ((BaseEntity)object).getId()!=null){
        T ety =findById(((BaseEntity)object).getId());
           if(ety!=null){
               throw new DataException("its looks like entity aleady eixists");
           }
        }


    return save(object);


    }

    public T update(T objectTopatch) {

        T ob = save(objectTopatch);
        System.out.println("successfully updated object " + objectTopatch);

        System.out.println("something went wrong in the update method");
        return ob;
    }

    public T patch(T objectTopatch) {

        if (objectTopatch == null)
            throw new DataException("there should exist  an object already");
        T ob = save(objectTopatch);
        System.out.println("successfully updated object " + objectTopatch);

        System.out.println("something went wrong in the update method");
        return ob;
    }

    public < ID extends Serializable> void deleteById(ID objId) {
//        T t =dao.getOne(objId);
        dao.delete((String)objId);
    }

    public void delete(T objId) {
//        T t =dao.getOne(objId);
        dao.delete(objId);
    }
}
