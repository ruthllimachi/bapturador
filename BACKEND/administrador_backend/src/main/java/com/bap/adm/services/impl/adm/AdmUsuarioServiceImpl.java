/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.impl.adm;

import com.bap.adm.dto.ConsultaParametros;
import com.bap.adm.dto.UserToken;
import com.bap.adm.exception.ExceptionInvalidCredential;
import com.bap.adm.model.adm.AdmUsuario;
import com.bap.adm.repo.adm.AdmUsuarioRepo;
import com.bap.adm.services.adm.AdmSessionService;
import com.bap.adm.services.adm.AdmSistemaService;
import com.bap.adm.services.adm.AdmUsuarioService;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class AdmUsuarioServiceImpl implements AdmUsuarioService {
    
    @Autowired
    private AdmUsuarioRepo repo;
    
    @Autowired
    private AdmSessionService service;
    
    @Autowired
    private AdmSistemaService admSistemaService;
    
    @Override
    public AdmUsuario registrar(AdmUsuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public AdmUsuario modificar(AdmUsuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public AdmUsuario leerPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<AdmUsuario> listar() {
        return repo.findAll();
    }
    
    @Override
    public void eliminar(AdmUsuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public UserToken authenticate(String login, String password) {
        List<AdmUsuario> lista = repo.findByLoginAndPassword(login, password);
        if (lista.isEmpty()) {
            try {
                throw new ExceptionInvalidCredential("There is an error in login or password");
            } catch (ExceptionInvalidCredential ex) {
                java.util.logging.Logger.getLogger(AdmUsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * ********VERIFICA LA EXISTENCIA DE UN TOKEN PARA EL USUARIO*******
         */
        UserToken userToken = new UserToken();
        String token = service.existsToken(lista.get(0).getIdUsuario());
        if (token == null) {
            userToken = service.creaToken(lista.get(0));
        } else {
            userToken.setLogin(login);
            userToken.setToken(token);
            userToken.setIsAutentificado(true);
            userToken.setIdUsuario(lista.get(0).getIdUsuario());
            userToken.setIdEmpresa(lista.get(0).getAdmPersona().getAdmEmpresa().getIdEmpresa());
        }
        
        return userToken;
    }
    
    @Override
    public ConsultaParametros consultaParametros(String login) {        
        ConsultaParametros consultaParametros = new ConsultaParametros();
        
        AdmUsuario admUsuario = repo.findOneByLogin(login);
        
        consultaParametros.setIdEmpresa(admUsuario.getAdmPersona().getAdmEmpresa().getIdEmpresa());
        consultaParametros.setIdUsuario(admUsuario.getIdUsuario());
        consultaParametros.setIdSucursal(admUsuario.getIdSucursal());
        consultaParametros.setIdPuntoVenta(admUsuario.getIdPuntoVenta());
        consultaParametros.setCodigoSistema(admSistemaService.listar().get(0).getCodigoSistema());
        consultaParametros.setNitEmpresa(admUsuario.getAdmPersona().getAdmEmpresa().getNitEmpresa());
        consultaParametros.setCodigoAmbiente(admUsuario.getAdmPersona().getAdmEmpresa().getCodigoAmbiente());
        consultaParametros.setTipoModalidadDefecto(admUsuario.getAdmPersona().getAdmEmpresa().getTipoModalidadDefecto());
        
        return consultaParametros;
    }
    
}
