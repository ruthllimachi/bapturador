/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiConfiguracionSucursal;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiConfiguracionSucursalService {

    ApiConfiguracionSucursal registrar(ApiConfiguracionSucursal t);    

    List<ApiConfiguracionSucursal> listar();  
    
    ApiConfiguracion getConfiguracionSucursalVigte(Long idSucursal);

}
