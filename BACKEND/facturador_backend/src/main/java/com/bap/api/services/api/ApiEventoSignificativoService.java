/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.model.api.ApiActividad;
import com.bap.api.configurate.AbstractEntity;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiActividadService extends AbstractEntity<ApiActividad> {
    
    List<ApiActividad> listarPorIdEmpresa(Long idEmpresa);
    
    ApiActividad leerPorIdEmpresaAndCodigoActividad(Long id, Long codigoActividad);

}
