/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiFacturaComercialExportacion;
import com.bap.api.services.api.ApiFacturaComercialExportacionService;
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
@RequestMapping(value = "/apiFacturaComercialExportacion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiFacturaComercialExportacionController {

    @Autowired
    private ApiFacturaComercialExportacionService servicio;

    @GetMapping
    public ResponseEntity<List<ApiFacturaComercialExportacion>> listar() {
        List<ApiFacturaComercialExportacion> lista = servicio.listar();
        return new ResponseEntity<List<ApiFacturaComercialExportacion>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiFacturaComercialExportacion> leerPorId(@PathVariable("id") Long id) {
        ApiFacturaComercialExportacion obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiFacturaComercialExportacion>(obj, HttpStatus.OK);
    }

    @GetMapping("/leerPorLogin/{login}/{codigoTipoDocumentoFiscal}/{codigoTipoDocumentoSector}/{codigoTipoModalidad}")
    public ResponseEntity<List<ApiFacturaComercialExportacion>> leerPorLogin(@PathVariable("login") String login, @PathVariable("codigoTipoDocumentoFiscal") Long codigoTipoDocumentoFiscal, @PathVariable("codigoTipoDocumentoSector") Long codigoTipoDocumentoSector, @PathVariable("codigoTipoModalidad") Long codigoTipoModalidad) {
        List<ApiFacturaComercialExportacion> lista = servicio.leerPorLogin(login, codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
        return new ResponseEntity<List<ApiFacturaComercialExportacion>>(lista, HttpStatus.OK);
    }
}
