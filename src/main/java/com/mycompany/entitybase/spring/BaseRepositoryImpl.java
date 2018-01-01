package com.mycompany.entitybase.spring;

import com.mycompany.entitybase.dao.SFDAO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 * Created by LENOVO PC on 16/5/2017.
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements SFDAO<T, ID> {

    private EntityManager entityManager;

    // There are two constructors to choose from, either can be used.
    public BaseRepositoryImpl(JpaEntityInformation entityInformation,
                              EntityManager entityManager) {
        super(entityInformation, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }


     public List<T> search(String column, Object value){
        if(!column.contains("c.")){
            column ="c."+column; //column should contain c. or else we should add it
        }
        if(value !=null && value.toString().contains("%")){
            return searchLike(column,value.toString());
        }
        return searchEq(column,value);
    }
    //    @Query("select c from #{#entityName} c where ?1 = ?2")//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
    private List<T> searchEq(String column,Object value){
        Query query = entityManager.createQuery("select c from "+getDomainClass().getSimpleName()+" c where " + column + " = ?1");
        query.setParameter(1,value);
        return query.getResultList();
    }
//
//    @Query("select c from #{#entityName} c where ?1 like ?2")//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions
    private List<T> searchLike(String column,String value){
        Query query = entityManager.createQuery("select c from "+getDomainClass().getSimpleName()+" c where " + column + " like ?1");
        query.setParameter(1,value);
        return query.getResultList();
    }

    @Override
    public Page<T> searchPageable(String column, Object value, Pageable pageable) {
        
        
//        TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("select c from "+getDomainClass().getSimpleName()+" c where " + column + " = ?1");
//        query.setParameter("column", column);
//        query.setParameter("", value);
        
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
        Root<T> c = cq.from(getDomainClass());
       
        Predicate p = cb.like(c.get(column.toString()),value.toString());
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {                 
                return  p;
            }
        };
        
        cq.where(p);
        TypedQuery<T> tq= entityManager.createQuery(cq);
        
        return readPage(tq, getDomainClass(), pageable, spec);
    }
    
    public List<T> goToPage(int pageNo){
        int defaultP=1000;
        Pageable pa = new PageRequest(pageNo,defaultP);
        return findAll(pa).getContent();
    }

    public List<T> goToPage(int pageNo,int size){
        Pageable pa = new PageRequest(pageNo,size);
        return findAll(pa).getContent();
    }

    @Override
    public Page<T> searchPageable(String column, Object value, Object value2, Pageable pageable) {
        return null;
    }



}