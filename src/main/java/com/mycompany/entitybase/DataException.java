/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitybase;

/**
 *
 * @author LENOVO PC
 */
public class DataException extends BaseRuntimeException {

    private Throwable throwable;
    
    public DataException(String exceptionMessge,Throwable e ) {
        super(exceptionMessge,e);
        this.throwable = e;
        e.printStackTrace();
    }



    public DataException(String exceptionMessge) {
        super(exceptionMessge,null);

    }
    
}
