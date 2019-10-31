/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.dto.Respuesta39266;
import com.bap.api.dto.Respuesta39268;
import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.services.api.ApiConfiguracionService;
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
@RequestMapping(value = "/apiConfiguracion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiConfiguracionController {

    @Autowired
    private ApiConfiguracionService servicio;

    @GetMapping("/solicitudCufd/{login}")
    public ResponseEntity<Respuesta39268> solicitudCufd(@PathVariable("login") String login) {
        Respuesta39268 obj = servicio.solicitudCUFD(login);
        if (obj == null) {
            throw new ModelNotFoundException("Error en la red u otro");
        }
        return new ResponseEntity<Respuesta39268>(obj, HttpStatus.OK);
    }

    @GetMapping("/sincronizarFechaHora/{login}")
    public ResponseEntity<Respuesta39266> sincronizarFechaHora(@PathVariable("login") String login) {
        Respuesta39266 obj = servicio.sincronizarFechaHora(login);
        if (obj == null) {
            throw new ModelNotFoundException("Error en la red u otro");
        }
        return new ResponseEntity<Respuesta39266>(obj, HttpStatus.OK);
    }

    @GetMapping("/getConfiguracionVigenteBySucursal/{idSucursal}")
    public ResponseEntity<ApiConfiguracion> getConfiguracionVigenteBySucursal(@PathVariable("idSucursal") Long idSucursal) {
        ApiConfiguracion obj = servicio.getConfiguracionVigenteBySucursal(idSucursal);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + idSucursal);
        }
        return new ResponseEntity<ApiConfiguracion>(obj, HttpStatus.OK);
    }

    @GetMapping("/getConfiguracionVigenteByPuntoVenta/{idPuntoVenta}")
    public ResponseEntity<ApiConfiguracion> getConfiguracionVigenteByPuntoVenta(@PathVariable("idPuntoVenta") Long idPuntoVenta) {
        ApiConfiguracion obj = servicio.getConfiguracionVigenteByPuntoVenta(idPuntoVenta);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + idPuntoVenta);
        }
        return new ResponseEntity<ApiConfiguracion>(obj, HttpStatus.OK);
    }

}
