/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.dto.CabeceraDTO;
import com.bap.api.dto.DocumentoFiscalDTO;
import com.bap.api.dto.Respuesta;
import com.bap.api.services.api.ApiDocumentoFiscalService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiDocumentoFiscal", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiDocumentoFiscalController {

    @Autowired
    private ApiDocumentoFiscalService servicio;

    @PostMapping("/registroFacturaEstandarIndividual")
    public Respuesta registroFacturaEstandarIndividual(@Valid @RequestBody DocumentoFiscalDTO documentoFiscalDTO) {
        return servicio.registroFacturaEstandarIndividual(documentoFiscalDTO);
    }
    
    @PostMapping("/anularFacturaEstandar")
    public Respuesta anularFacturaEstandar(@Valid @RequestBody CabeceraDTO cabeceraDTO) {
        return servicio.anularFacturaEstandar(cabeceraDTO);
    }    

    @PostMapping("/registroFacturaComercialExportacion")
    public Respuesta registroFacturaComercialExportacion(@Valid @RequestBody DocumentoFiscalDTO documentoFiscalDTO) {
        return servicio.registroFacturaComercialExportacion(documentoFiscalDTO);
    }
    
    @PostMapping("/anularFacturaComercialExportacion")
    public Respuesta anularFacturaComercialExportacion(@Valid @RequestBody CabeceraDTO cabeceraDTO) {
        return servicio.anularFacturaComercialExportacion(cabeceraDTO);
    }

    @PostMapping("/registroNotaFiscalCreditoDebito")
    public Respuesta registroNotaFiscalCreditoDebito(@Valid @RequestBody DocumentoFiscalDTO documentoFiscalDTO) {
        return servicio.registroNotaFiscalCreditoDebito(documentoFiscalDTO);
    }
    
    @PostMapping("/anularNotaFiscalCreditoDebito")
    public Respuesta anularNotaFiscalCreditoDebito(@Valid @RequestBody CabeceraDTO cabeceraDTO) {
        return servicio.anularNotaFiscalCreditoDebito(cabeceraDTO);
    }
    
}
