/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebito;
import java.util.List;

/**
 *
 * @author ruth //
 */
public interface ApiNotaFiscalCreditoDebitoService extends AbstractEntity<ApiNotaFiscalCreditoDebito> {

    List<ApiNotaFiscalCreditoDebito> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad);

    long verificaRepiteNotaFiscalCreditoDebitoPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroNotaCreditoDebito);

    long verificaRepiteNotaFiscalCreditoDebitoPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroNotaCreditoDebito);

    ApiNotaFiscalCreditoDebito findNotaFiscalCreditoDebitoPorSucursal(Long numeroNotaCreditoDebito, String numeroAutorizacionCuf, Long idSucursal, String fechaEmision);

    ApiNotaFiscalCreditoDebito findNotaFiscalCreditoDebitoPorPuntoVenta(Long numeroNotaCreditoDebito, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmision);
}
