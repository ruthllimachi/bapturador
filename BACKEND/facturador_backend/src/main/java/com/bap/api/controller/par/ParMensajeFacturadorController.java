/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.par;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.services.par.ParMensajeFacturadorService;
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
@RequestMapping(value = "/parMensajeFacturador", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ParMensajeFacturadorController {

    @Autowired
    private ParMensajeFacturadorService servicio;

    @GetMapping
    public ResponseEntity<List<ParMensajeFacturador>> listar() {
        List<ParMensajeFacturador> lista = servicio.listar();        
        return new ResponseEntity<List<ParMensajeFacturador>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ParMensajeFacturador> leerPorCodigo(@PathVariable("codigo") String codigo) {
        ParMensajeFacturador obj = servicio.leerPorCodigo(codigo);
        if (obj == null) {
            throw new ModelNotFoundException("CODIGO NO ENCONTRADO: " + codigo);
        }
        return new ResponseEntity<ParMensajeFacturador>(obj, HttpStatus.OK);
    }

}
