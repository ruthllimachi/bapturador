/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.model.api.ApiSincronizacion;
import com.bap.api.dto.RespuestaSincronizacion;
import java.util.List;
import com.bap.api.configurate.AbstractEntity;

/**
 *
 * @author ruth
 *
 */
public interface ApiSincronizacionService extends AbstractEntity<ApiSincronizacion> {

    List<RespuestaSincronizacion> sincronizacionDiaria(String login);

    List<RespuestaSincronizacion> codigosCatalogos(String grupo);

    RespuestaSincronizacion sincronizacionCodigoAutorizacion(String grupo, String login, int codigoAutorizacion);
    
    RespuestaSincronizacion solicitudNuevoProducto(String login, String descripcion);

//    Respuesta39118 solicitudNuevoProducto(String login, String descripcion);
//    
//    Respuesta39118 validacionSolicitudNuevoProducto(String login, int codigoSolicitud);

}
