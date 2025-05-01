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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author LENOVO PC
 */
public class BaseService<T extends BaseEntity> implements IService<T> {

    protected BaseDAO<T, Serializable> dao;// id parameter should be gererics id extents serialisable
    @Autowired
    private GenericCustomSearchDAO<T> searchDAO;

    @Value("${application.baseRest.allowCreateWithId:true}")
    private final boolean allowCreateWithId = Boolean.TRUE;

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

    public SearchResult<T> search(SearchRequest request) {
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


    public Optional<T> findById(String s) {
        return dao.findById(s);
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
            throw new DataException("persistence error", e);
        }
    }
    @Transactional
    public <S extends T> List<S>  saveAll(List<S> objects) {
        try {
            for (T t : objects) {
                validateEntity(t);
            }
            return dao.saveAll(objects);
        } catch (Exception e) {
            throw new DataException("persistence error", e);
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
        if (Objects.isNull(object)) {
            throw new DataException("Payload object cannot be null");
        }

        if (object.getId() != null) {
            if (!allowCreateWithId) {
                throw new DataException("For creation of object, object's payload"
                        + " should not contain id ");
            }
            if (findById(String.valueOf(object.getId())).isPresent()) {
                throw new DataException(" entity already exists");
            }
        }

        return save(object);
    }

    public T update(T objectTopatch) {

        T ob = save(objectTopatch);
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


    public void deleteAll() {
        dao.deleteAll();
    }

    public void validateEntity(T object) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
