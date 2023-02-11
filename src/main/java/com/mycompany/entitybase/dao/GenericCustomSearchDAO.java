package com.mycompany.entitybase.dao;

import com.mycompany.entitybase.model.SearchRequest;
import com.mycompany.entitybase.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;

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
        return genericSearchSpecification.applyPaging(entityManager, searchRequest, itemProjected);
    }
}
