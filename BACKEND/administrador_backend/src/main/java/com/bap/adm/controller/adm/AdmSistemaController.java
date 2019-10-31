/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.controller.adm;

import com.bap.adm.model.adm.AdmSistema;
import com.bap.adm.services.adm.AdmSistemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/admSistema", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AdmSistemaController {

    @Autowired
    private AdmSistemaService service;

    @GetMapping
    public List<AdmSistema> listar() {
        return service.listar();
    }

}
