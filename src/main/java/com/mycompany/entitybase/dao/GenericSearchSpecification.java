package com.mycompany.entitybase.dao;


import com.mycompany.entitybase.model.FilterElement;
import com.mycompany.entitybase.model.PageRequest;
import com.mycompany.entitybase.model.SearchRequest;
import com.mycompany.entitybase.model.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component//generic search component
public class GenericSearchSpecification<T> implements Specification<T> {
    private Class<T> persistentClass;

    private int pageSize=100;

    public GenericSearchSpecification(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    // constructor

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
//        for (String column : columns) {
//            Expression<String> expression = buildExpression(column, root);
//            Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(expression), "%" + search.toUpperCase() + "%");
//            predicates.add(predicate);
//        }

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Expression<String> buildExpression(String column, Path<T> path) {
        if (!column.contains("."))
            return path.get(column);

        String[] parts = column.split("\\.");
        int i = 0;
        for (; i < parts.length - 1; i++) {
            path = path.get(parts[i]);
        }

        return path.get(parts[i]);
    }

    private TypedQuery<T> getQuery(EntityManager entityManager, SearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> root = criteriaQuery.from(persistentClass);
        CriteriaQuery<T> where = applyIndexPagination(searchRequest, criteriaBuilder, root,
                applyFilter(searchRequest, criteriaBuilder, root,
                        criteriaQuery.select(root)));
        return entityManager.createQuery(applySorts(searchRequest, criteriaBuilder, root, where));
    }

    private CriteriaQuery<T> applyIndexPagination(SearchRequest searchRequest, CriteriaBuilder criteriaBuilder, Root<T> root, CriteriaQuery<T> where) {
        if (isIndexBasedPagination(searchRequest)) {
            PageRequest pageable = searchRequest.getPageable();
            Predicate and = criteriaBuilder.and(pageable.isOffSetAsc() ? criteriaBuilder.greaterThan(
                    root.get(pageable.getOffSetAttribute()),
                    pageable.getOffSetValue()) :
                    criteriaBuilder.lessThan(
                            root.get(pageable.getOffSetAttribute()),
                            pageable.getOffSetValue())
            );
            return where.where(and);
        }
        return where;
    }

    private boolean isIndexBasedPagination(SearchRequest searchRequest) {
        return searchRequest != null && searchRequest.getPageable() != null &&
                searchRequest.getPageable().getOffSetAttribute() != null && searchRequest.getPageable().getOffSetValue() != null;
    }

    private CriteriaQuery<T> applySorts(SearchRequest searchRequest, CriteriaBuilder criteriaBuilder, Root<T> root, CriteriaQuery<T> where) {
        return Optional.ofNullable(searchRequest).map(SearchRequest::getSortBy)
                .map(it -> where
                        .orderBy(it
                                .stream()
                                .map(t -> t.isAsc() ?
                                        criteriaBuilder.asc(root.get(t.getAttribute())) :
                                        criteriaBuilder.desc(root.get(t.getAttribute())))
                                .collect(Collectors.toList())))
                .orElse(where);
    }

    private CriteriaQuery applyFilter(SearchRequest searchRequest, CriteriaBuilder criteriaBuilder, Root<T> root, CriteriaQuery select) {
        return Optional.ofNullable(searchRequest).map(SearchRequest::getFilterBy)
                .map(it -> select.where(getPredicates(it, criteriaBuilder, root)))
                .orElse(select);
    }

    public SearchResult<T> applyPaging(EntityManager entityManager, SearchRequest searchRequest, long itemProjected) {
        TypedQuery<T> query = getQuery(entityManager, searchRequest);

        Optional.ofNullable(searchRequest).map(SearchRequest::getPageable).ifPresent(it -> {
            int pageNumber = 0;
            int pageSize = 10;//Todo from default environmental variable
            pageNumber = searchRequest.getPageable().getPageNumber();
            pageSize = searchRequest.getPageable().getPageSize();
            if (!isIndexBasedPagination(searchRequest)) {
                query.setFirstResult((pageNumber - 1) * pageSize);//
            }
            query.setMaxResults(pageSize);
        });
        query.setMaxResults(pageSize);

        List<T> resultList = query.getResultList();
        return new SearchResult<>(resultList,
                Optional.ofNullable(searchRequest)
                        .map(SearchRequest::getPageable)
                        .orElse(new PageRequest(1, resultList.size())),
                itemProjected);
    }

    public Long getCount(EntityManager entityManager, SearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(persistentClass);
        CriteriaQuery<Long> select = criteriaQuery.select(criteriaBuilder.count(root));
        CriteriaQuery<Long> where = applyFilter(searchRequest, criteriaBuilder, root, select);
        Query query = entityManager.createQuery(where);
        return (Long) query.getSingleResult();
    }

    private Predicate[] getPredicates(List<FilterElement> filters, CriteriaBuilder cb, Root<T> root) {
        return filters.stream().map((FilterElement filterElement) -> getPredicate(filterElement, cb, root)).toArray(Predicate[]::new);
    }

    private Predicate getPredicate(FilterElement filterElement, CriteriaBuilder cb, Root<T> root) {
        if (Objects.isNull(filterElement.getOps()) || "eq".equalsIgnoreCase(filterElement.getOps())) {
            return cb.equal(root.get(filterElement.getAttribute()), filterElement.getValue());
        }
        switch (filterElement.getOps().toLowerCase()) {
            case "grt":
                return cb.greaterThan(root.get(filterElement.getAttribute()), filterElement.getValue());
            case "grt=":
                return cb.greaterThanOrEqualTo(root.get(filterElement.getAttribute()), filterElement.getValue());
            case "less":
                return cb.lessThan(root.get(filterElement.getAttribute()), filterElement.getValue());
            case "less=":
                return cb.lessThanOrEqualTo(root.get(filterElement.getAttribute()), filterElement.getValue());
            case "between":
                return cb.between(root.get(filterElement.getAttribute()), filterElement.getValue(), filterElement.getValue2());
            default:
                return cb.equal(root.get(filterElement.getAttribute()), filterElement.getValue());
        }
    }
}
