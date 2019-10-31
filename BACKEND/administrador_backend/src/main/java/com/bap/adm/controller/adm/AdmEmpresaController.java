/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.controller.adm;

import com.bap.adm.dto.ConsultaParametros;
import com.bap.adm.exception.ModelNotFoundException;
import com.bap.adm.model.adm.AdmEmpresa;
import com.bap.adm.services.adm.AdmEmpresaService;
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
@RequestMapping(value = "/admEmpresa", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AdmEmpresaController {

    @Autowired
    private AdmEmpresaService servicio;

    @GetMapping
    public List<AdmEmpresa> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmEmpresa> leerPorId(@PathVariable("id") Long id) {
        AdmEmpresa obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<AdmEmpresa>(obj, HttpStatus.OK);
    }

    @GetMapping("/leerPorNitEmpresa/{nitEmpresa}")
    public ResponseEntity<ConsultaParametros> leerPorNitEmpresa(@PathVariable("nitEmpresa") Long nitEmpresa) {
        ConsultaParametros obj = servicio.leerPorNitEmpresa(nitEmpresa);
        if (obj == null) {
            throw new ModelNotFoundException("NIT NO ENCONTRADO: " + nitEmpresa);
        }
        return new ResponseEntity<ConsultaParametros>(obj, HttpStatus.OK);
    }

}
