/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.dto.RespuestaSincronizacion;
import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiSincronizacion;
import com.bap.api.services.api.ApiSincronizacionService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiSincronizacion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiSincronizacionController {

    @Autowired
    private ApiSincronizacionService servicio;

    @GetMapping
    public List<ApiSincronizacion> listar() {        
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSincronizacion> leerPorId(@PathVariable("id") Long id) {
        ApiSincronizacion obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiSincronizacion>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiSincronizacion obj) {
        ApiSincronizacion apiSincronizacion = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(apiSincronizacion.getIdSincronizacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/codigoCatalogos")
    public List<RespuestaSincronizacion> codigoCatalogos(@RequestParam("grupo") String grupo) {
        List<RespuestaSincronizacion> lista = servicio.codigosCatalogos(grupo);
        return lista;
    }

    @GetMapping("/sincronizacionDiaria/{login}")
    public ResponseEntity<List<RespuestaSincronizacion>> sincronizacionDiaria(@PathVariable("login") String login) {
        List<RespuestaSincronizacion> lista = servicio.sincronizacionDiaria(login);
        if (lista.isEmpty()){
             throw new ModelNotFoundException("Error en la red u otro");
        } 
        return new ResponseEntity<List<RespuestaSincronizacion>>(lista, HttpStatus.OK);
    }

    @GetMapping("/sincronizacionBySolicitud/{grupo}/{login}/{codigoAutorizacion}")
    public ResponseEntity<RespuestaSincronizacion> sincronizacionBySolicitud(@PathVariable("grupo") String grupo, @PathVariable("login") String login, @PathVariable("codigoAutorizacion") int codigoAutorizacion) {
        RespuestaSincronizacion obj = servicio.sincronizacionCodigoAutorizacion(grupo, login, codigoAutorizacion);
        if (obj == null) {
            throw new ModelNotFoundException("Error en la red u otro");
        }
        return new ResponseEntity<RespuestaSincronizacion>(obj, HttpStatus.OK);        
    }
}
