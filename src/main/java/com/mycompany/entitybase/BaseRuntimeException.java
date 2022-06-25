/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase;

/**
 * @author LENOVO PC
 */
public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String message, Throwable e) {
        super(message, e);
    }

}
