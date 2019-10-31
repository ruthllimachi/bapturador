/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiDosificacionPuntoVenta;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiDosificacionPuntoVentaService {

    ApiDosificacionPuntoVenta registrar(ApiDosificacionPuntoVenta t);

    List<ApiDosificacionPuntoVenta> listar();

    Entidad getDosificacionPuntoVentaVigte(Long idPuntoVenta);

    List<ApiDosificacionPuntoVenta> getDosificacionPuntoVentaPorIdEmpresa(Long idEmpresa);

}
