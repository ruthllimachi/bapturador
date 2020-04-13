/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.dto.ConsultaParametros;
import com.bap.api.dto.SolicitudCliente;

/**
 *
 * @author ruth
 */
public interface AdmConsultasService {

    ConsultaParametros consultarDatosUsuario(String login);

    boolean cudfVigente(String login);

    SolicitudCliente wsSin(String login);

    ConsultaParametros consultarDatosEmpresa(Long nitEmpresa);
}
