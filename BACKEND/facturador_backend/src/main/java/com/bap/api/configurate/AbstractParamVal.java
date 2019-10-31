package com.bap.api.configurate;

import java.util.List;

/**
 *
 * @author ruth
 */
public interface AbstractParamVal<T> {

    T leerPorCodigo(Long codigo);      
   
    List<T> listar();
    
}
