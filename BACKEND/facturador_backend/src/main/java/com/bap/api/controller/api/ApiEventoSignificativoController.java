/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.dto.Respuesta;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.model.api.ApiEventoSignificativo;
import com.bap.api.services.api.ApiEventoSignificativoService;
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

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiEventoSignificativo", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiEventoSignificativoController {

    @Autowired
    private ApiEventoSignificativoService servicio;

    @GetMapping
    public ResponseEntity<List<ApiEventoSignificativo>> listar() {
        List<ApiEventoSignificativo> lista = servicio.listar();
        return new ResponseEntity<List<ApiEventoSignificativo>>(lista, HttpStatus.OK);
    }

    @PostMapping("/inicioEventoSignificativo")
    public Respuesta registroInicioEventoSignificativo(@Valid @RequestBody SolicitudCliente t) {
        return servicio.registroInicioEventoSignificativo(t);
    }

    @PostMapping("/finEventoSignificativo")
    public Respuesta registroFinEventoSignificativo(@Valid @RequestBody SolicitudCliente t) {
        return servicio.registroFinEventoSignificativo(t);
    }
    
    @GetMapping("/consultaEventoSignificativo/{login}")
    public Respuesta consultaFinEventoSignificativo(@PathVariable("login") String login) {
        return servicio.consultaEventoSignificativo(login);
    }

}
