/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.adm;

import com.bap.adm.configurate.AbstractEntity;
import com.bap.adm.dto.UserToken;
import com.bap.adm.model.adm.AdmSession;
import com.bap.adm.model.adm.AdmUsuario;

/**
 *
 * @author ruth
 */
public interface AdmSessionService extends AbstractEntity<AdmSession> {

    String existsToken(Long idUsuario);

    UserToken creaToken(AdmUsuario admUsuario);
}
