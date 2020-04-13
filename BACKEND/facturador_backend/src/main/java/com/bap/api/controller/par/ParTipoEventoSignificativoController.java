/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.controller.par;



import com.bap.api.services.par.ParTipoClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jacqueline
 */
@RestController
@RequestMapping(value = "/parTipoCliente", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ParTipoClienteController {
    
    @Autowired
    private ParTipoClienteService parTipoClienteService;

    
}
