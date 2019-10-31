/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.par;

import com.bap.api.model.par.ParUnidadMedida;
import com.bap.api.services.par.ParUnidadMedidaService;
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
@RequestMapping(value = "/parUnidadMedida", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ParUnidadMedidaController {
    
    @Autowired
    private ParUnidadMedidaService servicio;

    @GetMapping
    public ResponseEntity<List<ParUnidadMedida>> listar() {
        List<ParUnidadMedida> lista = servicio.listar();
        return new ResponseEntity<List<ParUnidadMedida>>(lista,HttpStatus.OK);
    } 
    
    
}
