package com.techstart;

import com.mycompany.entitybase.dao.GenericCustomSearchDAO;
import com.mycompany.entitybase.spring.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;

/**
 * Created by jawa on 9/17/2020.
 */


@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BaseEntityConfig{

@Autowired
private EntityManager entityManager;
@Bean
public GenericCustomSearchDAO getGenericCustomSearchDAO(){
    return new GenericCustomSearchDAO(entityManager);
}


}
