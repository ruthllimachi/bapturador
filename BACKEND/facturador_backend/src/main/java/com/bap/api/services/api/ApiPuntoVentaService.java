/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.model.api.ApiPuntoVenta;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiPuntoVentaService extends AbstractEntity<ApiPuntoVenta> {

    List<ApiPuntoVenta> listarPorIdEmpresa(Long idEmpresa);

    List<ApiPuntoVenta> listarPorIdSucursal(Long IdSucursal);

    Respuesta registroPuntoVenta(SolicitudCliente t);
    
    Respuesta consultaPuntoVenta(SolicitudCliente t);
    
    Respuesta cierrePuntoVenta(SolicitudCliente t);

    ApiPuntoVenta leerPorIdSucursalAndCodigoPuntoVenta(Long idSucursal, Long codigoPuntoVenta);
}
