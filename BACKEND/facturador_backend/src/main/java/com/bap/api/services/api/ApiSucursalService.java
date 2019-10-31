/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiSucursal;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiSucursalService extends AbstractEntity<ApiSucursal> {

    List<ApiSucursal> listarPorIdEmpresa(Long idEmpresa);    
    
    List<ApiSucursal> listarByNoTieneCuis(Long idEmpresa);
    
    List<ApiSucursal> listaByPuntoVentaNoTieneCuis(Long idEmpresa);
    
    List<ApiSucursal> listaByEmpresaTieneCuisVigente(Long idEmpresa);
    
    ApiSucursal leerPorIdEmpresaAndCodigoSucursal(Long idEmpresa, Long codigoSucursal);
    
}
