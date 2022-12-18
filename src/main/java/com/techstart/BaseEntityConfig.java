package com.techstart;

import com.mycompany.entitybase.dao.GenericCustomSearchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * Created by jawa on 9/17/2020.
 */


@Configuration
public class BaseEntityConfig {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public GenericCustomSearchDAO getGenericCustomSearchDAO() {
        return new GenericCustomSearchDAO(entityManager);
    }

}
