/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiDosificacionPuntoVenta;
import com.bap.api.model.api.ApiDosificacionSucursal;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiDosificacionSucursalService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/** 
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiDosificacion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiDosificacionController {

    @Autowired
    private ApiDosificacionService servicio;

    @Autowired
    private ApiDosificacionSucursalService apiDosificacionSucursalService;

    @Autowired
    private ApiDosificacionPuntoVentaService apiDosificacionPuntoVentaService;

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiDosificacion obj) {
        ApiDosificacion ApiDosificacionSucursal = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ApiDosificacionSucursal.getIdDosificacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ApiDosificacion>> listar() {
        List<ApiDosificacion> lista = servicio.listar();
        return new ResponseEntity<List<ApiDosificacion>>(lista, HttpStatus.OK);
    }

    @PostMapping("/solicitudCuis")
    public ResponseEntity<Respuesta39117> solicitudCuisSucursal(@Valid @RequestBody SolicitudCliente t) {
        Respuesta39117 obj = servicio.solicitudCuis(t);
        if (obj == null) {
            throw new ModelNotFoundException("Error en la red u otro");
        }
        return new ResponseEntity<Respuesta39117>(obj, HttpStatus.OK);
    }

    @GetMapping("/getDosificacionVigenteBySucursal/{idSucursal}")
    public ResponseEntity<ApiDosificacion> getDosificacionVigenteBySucursal(@PathVariable("idSucursal") Long idSucursal) {
        ApiDosificacion obj = servicio.getDosificacionVigenteBySucursal(idSucursal);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + idSucursal);
        }
        return new ResponseEntity<ApiDosificacion>(obj, HttpStatus.OK);
    }

    @GetMapping("/getDosificacionVigenteByPuntoVenta/{idPuntoVenta}")
    public ResponseEntity<ApiDosificacion> getDosificacionVigenteByPuntoVenta(@PathVariable("idPuntoVenta") Long idPuntoVenta) {
        ApiDosificacion obj = servicio.getDosificacionVigenteBySucursal(idPuntoVenta);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + idPuntoVenta);
        }
        return new ResponseEntity<ApiDosificacion>(obj, HttpStatus.OK);
    }

    @GetMapping("/listaDosificacionSucursalPorIdEmpresa/{idEmpresa}")
    public ResponseEntity<List<ApiDosificacionSucursal>> listaDosificacionSucursalPorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        List<ApiDosificacionSucursal> lista = apiDosificacionSucursalService.getDosificacionSucursalPorIdEmpresa(idEmpresa);
        return new ResponseEntity<List<ApiDosificacionSucursal>>(lista, HttpStatus.OK);
    }

    @GetMapping("/listaDosificacionPuntoVentaPorIdEmpresa/{idEmpresa}")
    public ResponseEntity<List<ApiDosificacionPuntoVenta>> listaDosificacionPuntoVentaPorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        List<ApiDosificacionPuntoVenta> lista = apiDosificacionPuntoVentaService.getDosificacionPuntoVentaPorIdEmpresa(idEmpresa);
        return new ResponseEntity<List<ApiDosificacionPuntoVenta>>(lista, HttpStatus.OK);
    }
}
