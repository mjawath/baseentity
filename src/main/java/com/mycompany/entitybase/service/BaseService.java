/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase.service;

import com.mycompany.entitybase.BaseEntity;
import com.mycompany.entitybase.DataException;
import com.mycompany.entitybase.dao.BaseDAO;
import com.mycompany.entitybase.dao.GenericCustomSearchDAO;
import com.mycompany.entitybase.model.SearchRequest;
import com.mycompany.entitybase.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author LENOVO PC
 */
public class BaseService<T extends BaseEntity> implements IService<T> {

    protected BaseDAO<T, Serializable> dao;// id parameter should be gererics id extents serialisable
    @Autowired
    private GenericCustomSearchDAO<T> searchDAO;

    public BaseService() {
    }

    public BaseService(BaseDAO dao) {
        this.dao = dao;
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public List<T> search(String column, Object value) {
        return dao.search(column, value);
    }

    public SearchResult<T> search(SearchRequest request){
        return searchDAO.search(request);
    }

    public List<T> goToPage(int pageNo) {
        List<T> list = goToPage(pageNo, 100);
        return list;
    }

    public List<T> goToPage(int pageNo, int size) {
        List<T> list = dao.goToPage(pageNo, size);
        return list;
    }

    public Page<T> searchPageable(String column, Object value, Pageable pageable) {
        Page<T> page = dao.searchPageable(column, value, pageable);
        return page;
    }

    public List<T> search(String column, Object value, Object value2) {
        return null;
    }

    public Page<T> searchPageable(String column, Object value, Object value2, Pageable pageable) {
        return null;
    }


    public T findById(String s) {
        return dao.findById(s).orElseThrow(RuntimeException::new);
    }


    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public <S extends T> S save(S object) {
        try {
            validateEntity(object);
            return dao.save(object);
        } catch (Exception e) {
            throw new DataException("persistance error", e);
        }
    }

    public <S extends T> S saveInternal(S object) {
        try {
            validateEntity(object);
            return dao.save(object);
        } catch (Exception e) {
            throw new DataException("persistance error", e);
        }
    }

    @Transactional
    public T create(T object) {

        if (object instanceof BaseEntity) {
            final BaseEntity base = (BaseEntity) object;
            if (base.getId() != null) {
                throw new DataException("Creation object's payload"
                        + " should not contain id "
                        + "  entity error this is save ");
//                T ety = findById(object.getId());

            }
//            else if (base != null) {
//                throw new DataException("its looks like entity aleady eixists");
//            }
            return save(object);
        }
        throw new DataException("its looks like entity is not a baseentity");

    }

    public T update(T objectTopatch) {

        T ob = save(objectTopatch);
        System.out.println("successfully updated object " + objectTopatch);
        return ob;
    }

    public T patch(T objectTopatch) {

        if (objectTopatch == null)
            throw new DataException("there should exist  an object already");

        T ob = save(objectTopatch);
        System.out.println("successfully updated object " + objectTopatch);
        return ob;
    }

    public void deleteById(String objId) {
//        T t =dao.getOne(objId);
        dao.deleteById(objId);
    }

    public void delete(T objId) {
//        T t =dao.getOne(objId);
        dao.delete(objId);
    }

    public void validateEntity(T object) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
