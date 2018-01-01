/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase;

import org.springframework.http.HttpStatus;

/**
 *
 * @author LENOVO PC
 */
public class BaseRuntimeException extends RuntimeException{
    
    private HttpStatus http;
    
    public BaseRuntimeException(String message,Throwable e) {
        super(message, e);
    }
    
    public BaseRuntimeException(String exceptionMessge,Throwable e ,HttpStatus http) {
        this(exceptionMessge,e);
        this.http =http;
    }
    
}
