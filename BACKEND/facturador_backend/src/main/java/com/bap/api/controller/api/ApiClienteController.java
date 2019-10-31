/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiCliente;
import com.bap.api.model.api.ApiFactura;
import com.bap.api.services.api.ApiClienteService;
import com.bap.api.services.api.ApiFacturaService;
import java.net.URI;
import java.util.Date;
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
@RequestMapping(value = "/apiCliente", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiClienteController {

    @Autowired
    private ApiClienteService servicio;

    @Autowired
    private ApiFacturaService apiFacturaService;

    @GetMapping
    public List<ApiCliente> listar() {
        return servicio.listar();
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ApiCliente obj) {
        ApiCliente apiCliente = servicio.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(apiCliente.getIdCliente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody ApiCliente obj) {
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Long id) {
        ApiCliente obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("CODIGO NO ENCONTRADO: " + id);
        } else {
            servicio.eliminar(obj);
        }
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiCliente> leerPorId(@PathVariable("id") Long id) {
        ApiCliente obj = servicio.leerPorId(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiCliente>(obj, HttpStatus.OK);
    }

    @GetMapping("/listarClientePorIdEmpresa/{idEmpresa}")
    public List<ApiCliente> listarClientePorIdEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
        return servicio.listarClientePorIdEmpresa(idEmpresa);
    }

    @PostMapping("/darBajaCliente")
    public ResponseEntity<Object> darBajaCliente(@Valid @RequestBody ApiCliente obj) throws Exception {
        ApiFactura apiFactura = apiFacturaService.getFacturaByCliente(obj.getIdCliente());
        if (apiFactura == null) {

            ApiCliente apiCliente = servicio.leerPorId(obj.getIdCliente());
            obj.setFechaAlta(apiCliente.getFechaAlta());
            obj.setUsuarioAlta(apiCliente.getUsuarioAlta());
            obj.setFechaBaja(new Date());
            obj.setUsuarioBaja("test");
            servicio.modificar(obj);
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);

    }

    @PostMapping("/modificaCliente")
    public ResponseEntity<Object> modificaCliente(@Valid @RequestBody ApiCliente obj) throws Exception {
        ApiCliente apiCliente = servicio.leerPorId(obj.getIdCliente());
        obj.setFechaAlta(apiCliente.getFechaAlta());
        obj.setUsuarioAlta(apiCliente.getUsuarioAlta());
        obj.setFechaModificacion(new Date());
        obj.setUsuarioModificacion("test");
        servicio.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
