/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.model.api.ApiNotaFiscalCreditoDebito;
import com.bap.api.services.api.ApiNotaFiscalCreditoDebitoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiNotaFiscalCreditoDebito", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiNotaFiscalCreditoDebitoController {

    @Autowired
    private ApiNotaFiscalCreditoDebitoService servicio;
    
     @GetMapping
    public ResponseEntity<List<ApiNotaFiscalCreditoDebito>> listar() {
        List<ApiNotaFiscalCreditoDebito> lista = servicio.listar();
        return new ResponseEntity<List<ApiNotaFiscalCreditoDebito>>(lista, HttpStatus.OK);
    }

    @GetMapping("/verificaRepiteNotaFiscalCreditoDebitoPorSucursal/{idSucursal}/{codigoTipoDocumentoFiscal}/{codigoTipoDocumentoSector}/{codigoTipoModalidad}/{numeroNotaCreditoDebito}")
    public long verificaRepiteNotaFiscalCreditoDebitoPorSucursal(@PathVariable("idSucursal") Long idSucursal, @PathVariable("codigoTipoDocumentoFiscal") Long codigoTipoDocumentoFiscal, @PathVariable("codigoTipoDocumentoSector") Long codigoTipoDocumentoSector, @PathVariable("codigoTipoModalidad") Long codigoTipoModalidad, @PathVariable("numeroNotaCreditoDebito") Long numeroNotaCreditoDebito) {
        long cantidad = servicio.verificaRepiteNotaFiscalCreditoDebitoPorSucursal(idSucursal, codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad, numeroNotaCreditoDebito);
        return cantidad;
    }

    @GetMapping("/leerPorLogin/{login}/{codigoTipoDocumentoFiscal}/{codigoTipoDocumentoSector}/{codigoTipoModalidad}")
    public ResponseEntity<List<ApiNotaFiscalCreditoDebito>> leerPorLogin(@PathVariable("login") String login, @PathVariable("codigoTipoDocumentoFiscal") Long codigoTipoDocumentoFiscal, @PathVariable("codigoTipoDocumentoSector") Long codigoTipoDocumentoSector, @PathVariable("codigoTipoModalidad") Long codigoTipoModalidad) {
        List<ApiNotaFiscalCreditoDebito> lista = servicio.leerPorLogin(login, codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
        return new ResponseEntity<List<ApiNotaFiscalCreditoDebito>>(lista, HttpStatus.OK);
    }
}
