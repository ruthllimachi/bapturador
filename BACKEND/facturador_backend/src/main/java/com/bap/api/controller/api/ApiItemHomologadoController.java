/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiItemHomologado;
import com.bap.api.services.api.ApiItemHomologadoService;
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
@RequestMapping(value = "/apiItemHomologado", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiItemHomologadoController {

    @Autowired
    private ApiItemHomologadoService servicio;

    @GetMapping
    public List<ApiItemHomologado> listar() {
        return servicio.listar();
    }
    
     @GetMapping("/listarHomologadoByItem/{idItem}")
    public List<ApiItemHomologado> listarHomologadoByItem(@PathVariable("idItem") Long idItem) throws Exception {
        return servicio.getHomologadoByItem(idItem);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiItemHomologado obj) {
        ApiItemHomologado apiItemHomologado = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(apiItemHomologado.getIdItemHomologado()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody ApiItemHomologado obj) throws Exception {
        ApiItemHomologado apiHomolagado = servicio.leerPorId(obj.getIdItemHomologado());        
        obj.setFechaAlta(apiHomolagado.getFechaAlta());
        obj.setUsuarioAlta(apiHomolagado.getUsuarioAlta());
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Long id) {
        ApiItemHomologado obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("CODIGO NO ENCONTRADO: " + id);
        } else {
            servicio.eliminar(obj);
        }
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @PostMapping("/darBajaHomologado")
    public ResponseEntity<Object> darBajaHomologado(@Valid @RequestBody ApiItemHomologado obj) throws Exception {
        ApiItemHomologado apiHomolagado = servicio.leerPorId(obj.getIdItemHomologado());
        obj.setFechaAlta(apiHomolagado.getFechaAlta());
        obj.setUsuarioAlta(apiHomolagado.getUsuarioAlta());
        
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    
     @PostMapping("/modificaHomologado")
    public ResponseEntity<Object> modificaCliente(@Valid @RequestBody ApiItemHomologado obj) throws Exception {
        ApiItemHomologado apiItemHomologado = servicio.leerPorId(obj.getIdItemHomologado());
        obj.setFechaAlta(apiItemHomologado.getFechaAlta());
        obj.setUsuarioAlta(apiItemHomologado.getUsuarioAlta());
        
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
