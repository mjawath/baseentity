package com.mycompany.entitybase.dao;

import com.mycompany.entitybase.model.SearchRequest;
import com.mycompany.entitybase.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component// generic dao for search
public class GenericCustomSearchDAO<T> {

    @Autowired
    private EntityManager entityManager;

    public GenericCustomSearchDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SearchResult<T> search(SearchRequest searchRequest) {
        GenericSearchSpecification<T> genericSearchSpecification = new GenericSearchSpecification<>(searchRequest.getPersistenceClass());
        Long itemProjected = genericSearchSpecification.getCount(entityManager, searchRequest);

//        if (searchRequest.getColumn() != null && searchRequest.getValue() != null && searchRequest.getValue2() != null) {
//            list.addAll(service.search(searchRequest.getColumn(),  searchRequest.getValue(), searchRequest.getValue2()));
//        } else if (searchRequest.getColumn() != null && searchRequest.getValue() != null) {
//            list.addAll(service.search(searchRequest.getColumn(), searchRequest.getValue()));
//        } else {
//            list.addAll(service.findAll());
//        }
        return genericSearchSpecification.applyPaging(entityManager, searchRequest, itemProjected);
    }
}
