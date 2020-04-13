/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiItem;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiItemService extends AbstractEntity<ApiItem> {

    List<ApiItem> listarPorIdEmpresa(Long idEmpresa);

    ApiItem leerPorCodigoSolicitud(Long idEmpresa, Long codigoSolicitud);

    List<Object[]> listarDatos(Long idEmpresa);

}
