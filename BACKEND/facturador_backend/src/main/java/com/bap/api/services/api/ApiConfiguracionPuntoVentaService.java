/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiConfiguracionPuntoVenta;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiConfiguracionPuntoVentaService {

    ApiConfiguracionPuntoVenta registrar(ApiConfiguracionPuntoVenta t);
    
    List<ApiConfiguracionPuntoVenta> listar();
    
    Entidad getConfiguracionPuntoVentaVigte(Long idPuntoVenta);
    
}
