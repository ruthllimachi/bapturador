/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.model.api.ApiDosificacion;

/**
 *
 * @author ruth
 */
public interface ApiDosificacionService extends AbstractEntity<ApiDosificacion> {

    Respuesta solicitudCuis(SolicitudCliente t);
    
    Respuesta cierreOperaciones(SolicitudCliente t);

    ApiDosificacion getDosificacionVigenteBySucursal(Long idSucursal);

    ApiDosificacion getDosificacionVigenteByPuntoVenta(Long idPuntoVenta);
}
