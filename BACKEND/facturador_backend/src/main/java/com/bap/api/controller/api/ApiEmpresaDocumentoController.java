/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.model.api.ApiEmpresaDocumento;
import com.bap.api.services.api.ApiEmpresaDocumentoService;
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
@RequestMapping(value = "/apiEmpresaDocumento", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiEmpresaDocumentoController {

    @Autowired
    private ApiEmpresaDocumentoService servicio;

    @GetMapping
    public ResponseEntity<List<ApiEmpresaDocumento>> listar() {
        List<ApiEmpresaDocumento> lista = servicio.listar();
        return new ResponseEntity<List<ApiEmpresaDocumento>>(lista, HttpStatus.OK);
    }

    @GetMapping("/documentoSectorPorEmpresa/{idEmpresa}/{codigoDocumentoFiscal}/{codigoDocumentoSector}/{codigoTipoModalidad}")
    public ResponseEntity<ApiEmpresaDocumento> documentoSectorPorEmpresa(@PathVariable("idEmpresa") Long idEmpresa,
            @PathVariable("codigoDocumentoFiscal") Long codigoDocumentoFiscal,
            @PathVariable("codigoDocumentoSector") Long codigoDocumentoSector,
            @PathVariable("codigoTipoModalidad") Long codigoTipoModalidad) {
        ApiEmpresaDocumento obj = servicio.leerPorTipos(idEmpresa, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad);
        return new ResponseEntity<ApiEmpresaDocumento>(obj, HttpStatus.OK);
    }
}
