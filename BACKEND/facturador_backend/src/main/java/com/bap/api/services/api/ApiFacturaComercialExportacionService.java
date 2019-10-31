/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiFacturaComercialExportacion;
import java.util.List;

/**
 *
 * @author ruth //
 */
public interface ApiFacturaComercialExportacionService extends AbstractEntity<ApiFacturaComercialExportacion> {

    long verificaRepiteFacturaComercialExportacionPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura);

    long verificaRepiteFacturaComercialExportacionPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura);

    List<ApiFacturaComercialExportacion> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad);

    ApiFacturaComercialExportacion findFacturaComercialExportacionPorSucursal(Long numeroFactura, String numeroAutorizacionCuf, Long idSucursal, String fechaEmisionFactura);

    ApiFacturaComercialExportacion findFacturaComercialExportacionPorPuntoVenta(Long numeroFactura, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmisionFactura);

}
