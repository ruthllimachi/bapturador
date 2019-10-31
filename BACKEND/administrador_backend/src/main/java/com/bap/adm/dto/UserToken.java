/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.dto;

import java.util.Map;

/**
 *
 * @author ruth
 */
public class UserToken {

    private String login;

    private String token;

    private Map<String, String> atributosPerfil;

    private Long idUsuario;

    private Long idEmpresa;
    
    private boolean isAutentificado;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the atributosPerfil
     */
    public Map<String, String> getAtributosPerfil() {
        return atributosPerfil;
    }

    /**
     * @param atributosPerfil the atributosPerfil to set
     */
    public void setAtributosPerfil(Map<String, String> atributosPerfil) {
        this.atributosPerfil = atributosPerfil;
    }

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idEmpresa
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the isAutentificado
     */
    public boolean isIsAutentificado() {
        return isAutentificado;
    }

    /**
     * @param isAutentificado the isAutentificado to set
     */
    public void setIsAutentificado(boolean isAutentificado) {
        this.isAutentificado = isAutentificado;
    }

}
