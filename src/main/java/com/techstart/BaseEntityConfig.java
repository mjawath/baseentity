package com.techstart;

import com.mycompany.entitybase.spring.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jawa on 9/17/2020.
 */


@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BaseEntityConfig{






}
