/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.dto.DocumentoFiscalDTO;
import com.bap.api.dto.Respuesta;

/**
 *
 * @author ruth
 */
public interface ApiDocumentoFiscalService {

    Respuesta registraFacturaEstandar(DocumentoFiscalDTO documentoFiscalDTO);

//    Respuesta anularFacturaEstandar(CabeceraDTO cabeceraDTO);
//
//    Respuesta registroFacturaComercialExportacion(DocumentoFiscalDTO documentoFiscalDTO);
//
//    Respuesta registroNotaFiscalCreditoDebito(DocumentoFiscalDTO documentoFiscalDTO);
//
//    Respuesta anularFacturaComercialExportacion(CabeceraDTO cabeceraDTO);
//    
//    Respuesta anularNotaFiscalCreditoDebito(CabeceraDTO cabeceraDTO);
}
