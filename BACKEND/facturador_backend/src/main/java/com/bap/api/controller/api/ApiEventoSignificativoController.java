/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.services.api.ApiSucursalService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiSucursal", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiSucursalController {

    @Autowired
    private ApiSucursalService servicio;

    @GetMapping
    public ResponseEntity<List<ApiSucursal>> listar() {
        List<ApiSucursal> lista = servicio.listar();
        return new ResponseEntity<List<ApiSucursal>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiSucursal> leerPorId(@PathVariable("id") Long id) {
        ApiSucursal obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiSucursal>(obj, HttpStatus.OK);
    }

    @GetMapping("/listarPorIdEmpresa/{idEmpresa}")
    public List<ApiSucursal> listarPorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarPorIdEmpresa(idEmpresa);
    }
    
    @GetMapping("/listaBySucursalNoTieneCuis/{idEmpresa}")
    public List<ApiSucursal> listaBySucursalNoTieneCuis(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarByNoTieneCuis(idEmpresa);
    }
    
    @GetMapping("/listaByPuntoVentaNoTieneCuis/{idEmpresa}")
    public List<ApiSucursal> listaByPuntoVentaNoTieneCuis(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listaByPuntoVentaNoTieneCuis(idEmpresa);
    }
    
    @GetMapping("/listaByEmpresaTieneCuisVigente/{idEmpresa}")
    public List<ApiSucursal> listaByEmpresaTieneCuisVigente(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listaByEmpresaTieneCuisVigente(idEmpresa);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiSucursal obj) {
        ApiSucursal apiSucursal = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(apiSucursal.getIdSucursal()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody ApiSucursal obj) {
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Long id) {
        ApiSucursal obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("CODIGO NO ENCONTRADO: " + id);
        } else {
            servicio.eliminar(obj);
        }
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

}
