/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.model.api.ApiEventoSignificativo;
import com.bap.api.configurate.AbstractEntity;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.SolicitudCliente;

/**
 *
 * @author ruth
 */
public interface ApiEventoSignificativoService extends AbstractEntity<ApiEventoSignificativo> {

    ApiEventoSignificativo leerPorCodigoRecepcion(Long codigoRecepcion);

    Respuesta registroInicioEventoSignificativo(SolicitudCliente t);
    
    Respuesta registroFinEventoSignificativo(SolicitudCliente t);
    
    Respuesta consultaEventoSignificativo(String login);
}
