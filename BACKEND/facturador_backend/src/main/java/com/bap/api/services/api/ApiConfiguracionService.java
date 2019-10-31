/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.configurate.AbstractEntity;
import com.bap.api.dto.Respuesta39266;
import com.bap.api.dto.Respuesta39268;

/**
 *
 * @author ruth
 */
public interface ApiConfiguracionService extends AbstractEntity<ApiConfiguracion> {

    Respuesta39268 solicitudCUFD(String login);

    Respuesta39266 sincronizarFechaHora(String login);

    ApiConfiguracion getConfiguracionVigenteBySucursal(Long idSucursal);

    ApiConfiguracion getConfiguracionVigenteByPuntoVenta(Long idPuntoVenta);

}
