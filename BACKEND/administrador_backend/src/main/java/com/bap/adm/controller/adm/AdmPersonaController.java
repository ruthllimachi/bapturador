/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.controller.adm;

import com.bap.adm.model.adm.AdmPersona;
import com.bap.adm.services.adm.AdmPersonaService;
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
@RequestMapping(value = "/admPersona", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AdmPersonaController {

    @Autowired
    private AdmPersonaService service;

    @GetMapping
    public List<AdmPersona> listar() {
        return service.listar();
    }
}
