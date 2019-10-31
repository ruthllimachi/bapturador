/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiItemHomologado;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiItemHomologadoService extends AbstractEntity<ApiItemHomologado> {

    ApiItemHomologado leerPorIdEmpresaAndCodigoProducto(Long idEmpresa, String codigoProducto);
    
    List<ApiItemHomologado> getHomologadoByItem(Long idItem) throws Exception;
}
