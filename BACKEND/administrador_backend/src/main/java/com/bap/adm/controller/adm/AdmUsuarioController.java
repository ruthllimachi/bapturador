/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.controller.adm;

import com.bap.adm.dto.ConsultaParametros;
import com.bap.adm.dto.UserToken;
import com.bap.adm.exception.ModelNotFoundException;
import com.bap.adm.model.adm.AdmUsuario;
import com.bap.adm.services.adm.AdmUsuarioService;
import java.util.List;
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
@RequestMapping(value = "/admUsuario", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AdmUsuarioController {

    @Autowired
    private AdmUsuarioService service;

    @GetMapping
    public List<AdmUsuario> listar() {
        return service.listar();
    }

    @PostMapping("/authenticate")
    public UserToken authenticate(@RequestBody AdmUsuario admUsuario) {
        UserToken userToken = service.authenticate(admUsuario.getLogin(), admUsuario.getPassword());
        return userToken;
    }

    @GetMapping("/getParametros/{login}")
    public ResponseEntity<ConsultaParametros> getParametros(@PathVariable("login") String login) {
        ConsultaParametros obj = service.consultaParametros(login);
        if (obj == null) {
            throw new ModelNotFoundException("NO ENCONTRADO: " + login);
        }
        return new ResponseEntity<ConsultaParametros>(obj, HttpStatus.OK);
    }

}
