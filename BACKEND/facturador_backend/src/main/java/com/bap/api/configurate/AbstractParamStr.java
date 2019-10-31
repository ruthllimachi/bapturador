package com.bap.api.configurate;

import java.util.List;

/**
 *
 * @author ruth
 */
public interface AbstractParamStr<T> {    
    
    T leerPorCodigo(String codigo);   

    List<T> listar();
    
}
