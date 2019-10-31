/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.par;


import com.bap.api.model.par.ParTipoDocumentoIdentidad;
import com.bap.api.services.par.ParTipoDocumentoIdentidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jacqueline
 */
@RestController
@RequestMapping(value = "/parTipoDocumentoIdentidad", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ParTipoDocumentoIdentidadController {
    
    @Autowired
    private ParTipoDocumentoIdentidadService servicio;

    @GetMapping
    public ResponseEntity<List<ParTipoDocumentoIdentidad>> listar() {
        List<ParTipoDocumentoIdentidad> lista = servicio.listar();
        return new ResponseEntity<List<ParTipoDocumentoIdentidad>>(lista, HttpStatus.OK);
    } 
    
}
