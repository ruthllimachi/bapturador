/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiDosificacionSucursal;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiDosificacionSucursalService {

    ApiDosificacionSucursal registrar(ApiDosificacionSucursal t);

    List<ApiDosificacionSucursal> listar();

    Entidad getDosificacionSucursalVigte(Long idSucursal);

    List<ApiDosificacionSucursal> getDosificacionSucursalPorIdEmpresa(Long idEmpresa);

}
