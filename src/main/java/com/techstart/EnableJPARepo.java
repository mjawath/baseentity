package com.techstart;

import com.mycompany.entitybase.spring.BaseRepositoryImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.DefaultRepositoryBaseClass;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by jawa on 9/17/2020.
 */
@Target(value = {ElementType.TYPE})
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public @interface EnableJPARepo  {
//    Class<?> repositoryBaseClass() default DefaultRepositoryBaseClass.class;

    @AliasFor(
            annotation = EnableJpaRepositories.class , attribute = "basePacksages"
    )
    String[] basePackages() default {};



}
