/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase.dao;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDAOJPA<T, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> persistentClass;

    public BaseDAOJPA(Class<T> domainClass) {
        super();
        this.persistentClass = domainClass;//(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public T findById(ID id) {
        return (T) entityManager.find(persistentClass, id);
    }

    public T getOne(ID id) {
        return entityManager.getReference(persistentClass, id);
    }

    public <S extends T> S save(S obj) {
        entityManager.persist(obj);
        return obj;
    }

//    @Override
    public List<T> search(String column,String value) {
        return null;
    }

    public List<T> search(){
        return null;
    }

    public void delete(T obj) {
        entityManager.remove(obj);
    }

    public T getByKey(ID key) {
        return (T) entityManager.find(persistentClass, key);

    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @PostConstruct
    public void postConstruct() {
        //TODO - overrides
    }

//    @Override
    public List<T> findAll() {
            return null;
    }
}
