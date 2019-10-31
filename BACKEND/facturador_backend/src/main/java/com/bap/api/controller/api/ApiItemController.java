/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiItem;
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
import com.bap.api.services.api.ApiItemService;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiItem", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiItemController {

    @Autowired
    private ApiItemService servicio;

    @GetMapping
    public List<ApiItem> listar() {
        return servicio.listar();
    }

    @GetMapping("/{idEmpresa}")
    public List<ApiItem> listarPorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarPorIdEmpresa(idEmpresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiItem> leerPorId(@PathVariable("id") Long id) {
        ApiItem obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiItem>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiItem obj) {
        ApiItem apiActividadEmpresa = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(apiActividadEmpresa.getIdItem()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody ApiItem obj) {
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Long id) {
        ApiItem obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        } else {
            servicio.eliminar(obj);
        }
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @GetMapping("/listarItemPorIdEmpresa/{idEmpresa}")
    public List<ApiItem> listarItemPorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarPorIdEmpresa(idEmpresa);
    }

    @GetMapping("/listarItemDatos/{idEmpresa}")
    public List<Object[]> listarItemDatos(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarDatos(idEmpresa);
    }

}
