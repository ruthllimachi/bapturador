/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.api;

import com.bap.api.exception.ModelNotFoundException;
import com.bap.api.model.api.ApiFactura;
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
import com.bap.api.services.api.ApiFacturaService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/apiFactura", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ApiFacturaController {

    @Autowired
    private ApiFacturaService servicio;

    @GetMapping
    public ResponseEntity<List<ApiFactura>> listar() {
        List<ApiFactura> lista = servicio.listar();
        return new ResponseEntity<List<ApiFactura>>(lista, HttpStatus.OK);
    }

    @GetMapping("/leerPorLogin/{login}/{codigoTipoDocumentoFiscal}/{codigoTipoDocumentoSector}/{codigoTipoModalidad}")
    public ResponseEntity<List<ApiFactura>> leerPorLogin(@PathVariable("login") String login, @PathVariable("codigoTipoDocumentoFiscal") Long codigoTipoDocumentoFiscal, @PathVariable("codigoTipoDocumentoSector") Long codigoTipoDocumentoSector, @PathVariable("codigoTipoModalidad") Long codigoTipoModalidad) {
        List<ApiFactura> lista = servicio.leerPorLogin(login, codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
        return new ResponseEntity<List<ApiFactura>>(lista, HttpStatus.OK);
    }

    @GetMapping("/verificaRepiteFacturaPorSucursal/{idSucursal}/{codigoTipoDocumentoFiscal}/{codigoTipoDocumentoSector}/{codigoTipoModalidad}/{numeroFactura}")
    public long verificaRepiteFacturaPorSucursal(@PathVariable("idSucursal") Long idSucursal, @PathVariable("codigoTipoDocumentoFiscal") Long codigoTipoDocumentoFiscal, @PathVariable("codigoTipoDocumentoSector") Long codigoTipoDocumentoSector, @PathVariable("codigoTipoModalidad") Long codigoTipoModalidad, @PathVariable("numeroFactura") Long numeroFactura) {
        long cantidad = servicio.verificaRepiteFacturaPorSucursal(idSucursal, codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad, numeroFactura);
        return cantidad;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ApiFactura> leerPorId(@PathVariable("id") Long id) {
//        ApiFactura obj = servicio.leerPorId(id);
//        if (obj == null) {
//            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
//        }
//        return new ResponseEntity<ApiFactura>(obj, HttpStatus.OK);
//    }
    @GetMapping("getFacturaByCliente/{id}")
    public ResponseEntity<ApiFactura> leerPorIdCliente(@PathVariable("id") Long id) throws Exception {
        ApiFactura obj = servicio.getFacturaByCliente(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<ApiFactura>(obj, HttpStatus.OK);
    }
//    @RequestMapping(method = RequestMethod.POST, path = "/generateCert/{id}", 
//    produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})

    @CrossOrigin
    @GetMapping(value = "/generarReporte/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporte(@PathVariable("id") Long id) {
        byte[] data = null;
        data = servicio.facturaEstandarPdf(id);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

//    @GetMapping("findFactura/{numeroFactura}/{numeroAutorizacionCuf}/{fechaEmisionFactura}")
//    public ResponseEntity<ApiFactura> findFactura(@PathVariable("numeroFactura") Long numeroFactura, @PathVariable("numeroAutorizacionCuf") String numeroAutorizacionCuf, @PathVariable("fechaEmisionFactura") String fechaEmisionFactura) {  
//        ApiFactura obj = servicio.findFactura(numeroFactura, numeroAutorizacionCuf, fechaEmisionFactura);
//        if (obj == null) {
//            throw new ModelNotFoundException("FACTURA NO ENCONTRADO ");
//        }
//        return new ResponseEntity<ApiFactura>(obj, HttpStatus.OK);
//    }
    
//    @GetMapping("findFactura")
//    public ResponseEntity<ApiFactura> findFactura(@RequestParam("numeroFactura") Long numeroFactura, @RequestParam("numeroAutorizacionCuf") String numeroAutorizacionCuf, @RequestParam("fechaEmisionFactura") String fechaEmisionFactura) {  
//        ApiFactura obj = servicio.findFactura(numeroFactura, numeroAutorizacionCuf, fechaEmisionFactura);
//        if (obj == null) {
//            throw new ModelNotFoundException("FACTURA NO ENCONTRADO ");
//        }
//        return new ResponseEntity<ApiFactura>(obj, HttpStatus.OK);
//    }

}
