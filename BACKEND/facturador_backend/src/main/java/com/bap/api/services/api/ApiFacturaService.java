/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiFactura;
import java.util.List;

/**
 *
 * @author ruth //
 */
public interface ApiFacturaService extends AbstractEntity<ApiFactura> {

    List<ApiFactura> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad);

    long verificaRepiteFacturaPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura);

    long verificaRepiteFacturaPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura);

    ApiFactura getFacturaByCliente(Long idCliente) throws Exception;

    byte[] facturaEstandarPdf(Long idFactura);

    //ApiFactura findFactura(Long numeroFactura, String numeroAutorizacionCuf, String fechaEmisionFactura);
    ApiFactura findFacturaPorSucursal(Long numeroFactura, String numeroAutorizacionCuf, Long idSucursal, String fechaEmisionFactura);

    ApiFactura findFacturaPorPuntoVenta(Long numeroFactura, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmisionFactura);

}
