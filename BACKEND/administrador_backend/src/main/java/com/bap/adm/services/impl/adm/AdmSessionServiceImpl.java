/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.impl.adm;

import com.bap.adm.configurate.JwtCypher;
import com.bap.adm.dto.UserToken;
import com.bap.adm.model.adm.AdmSession;
import com.bap.adm.model.adm.AdmUsuario;
import com.bap.adm.repo.adm.AdmSessionRepo;
import com.bap.adm.services.adm.AdmSessionService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ruth
 */
@Service
public class AdmSessionServiceImpl implements AdmSessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmSessionServiceImpl.class);

    @Autowired
    private AdmSessionRepo repo;

    @Override
    public AdmSession registrar(AdmSession t) {
        return repo.save(t);
    }

    @Override
    public AdmSession modificar(AdmSession t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmSession leerPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdmSession> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(AdmSession t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String splitJWTToken(String token) {   //Let's split the token
        token = token.replace(".", "#");
        String[] tokns = token.split("#");
        return tokns[1];
    }

    @Override
    public String existsToken(Long idUsuario) {
        List<AdmSession> lista = repo.findUsuario(idUsuario);
        if (!lista.isEmpty()) {
            AdmSession admSession = lista.get(0);
            return splitJWTToken(admSession.getToken());
        } else {
            return null;
        }
    }

    @Override
    public UserToken creaToken(AdmUsuario admUsuario) {

        UserToken userToken = new UserToken();
        userToken.setLogin(admUsuario.getLogin());
        userToken.setIdUsuario(admUsuario.getIdUsuario());
        userToken.setIdEmpresa(admUsuario.getAdmPersona().getAdmEmpresa().getIdEmpresa());
        userToken.setIsAutentificado(true);
        String token = JwtCypher.getToken(userToken);
        LOGGER.info("token:  " + token);

        AdmSession admSession = new AdmSession();
        admSession.setAdmUsuario(admUsuario);
        admSession.setToken(token);
        admSession.setFechaExpiracion(new Date());
        admSession.setFechaAlta(new Date());
        admSession.setUsuarioAlta("adm");
        
        repo.save(admSession);
        userToken.setToken(splitJWTToken(token));
        return userToken;
    }

}
