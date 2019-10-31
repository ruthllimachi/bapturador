/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.adm;

import com.bap.adm.configurate.AbstractEntity;
import com.bap.adm.dto.ConsultaParametros;
import com.bap.adm.dto.UserToken;
import com.bap.adm.model.adm.AdmUsuario;

/**
 *
 * @author ruth
 */
public interface AdmUsuarioService extends AbstractEntity<AdmUsuario> {
    
    UserToken authenticate(String login, String password);
    
    ConsultaParametros consultaParametros(String login);

}
