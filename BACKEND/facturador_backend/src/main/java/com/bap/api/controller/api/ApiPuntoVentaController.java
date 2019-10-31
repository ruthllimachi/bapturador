/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.services.api.ApiPuntoVentaService;
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
@RequestMapping(value = "/apiPuntoVenta", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiPuntoVentaController {

    @Autowired
    private ApiPuntoVentaService servicio;

    @GetMapping
    public List<ApiPuntoVenta> listar() {
        return servicio.listar();
    }

    @GetMapping("/listaPorIdEmpresa/{idEmpresa}")
    public List<ApiPuntoVenta> listarPorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarPorIdEmpresa(idEmpresa);
    }

    @GetMapping("/listarPorIdSucursal/{idSucursal}")
    public List<ApiPuntoVenta> listarPorIdSucursal(@PathVariable("idSucursal") Long idSucursal) {
        return servicio.listarPorIdSucursal(idSucursal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiPuntoVenta> leerPorId(@PathVariable("id") Long id) {
        ApiPuntoVenta obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiPuntoVenta>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiPuntoVenta obj) {
        ApiPuntoVenta apiPuntoVenta = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(apiPuntoVenta.getIdPuntoVenta()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody ApiPuntoVenta obj) {
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Long id) {
        ApiPuntoVenta obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("CODIGO NO ENCONTRADO: " + id);
        } else {
            servicio.eliminar(obj);
        }
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @PostMapping("/registrarPuntoVenta")
    public Respuesta39117 registrarPuntoVenta(@Valid @RequestBody SolicitudCliente t) {
        return servicio.registroPuntoVenta(t);
    }
}
