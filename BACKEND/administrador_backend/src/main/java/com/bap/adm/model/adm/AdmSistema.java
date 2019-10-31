/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.model.adm;

import com.bap.adm.configurate.OUI;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "adm_sistema")
public class AdmSistema implements Serializable {

    private static final long serialVersion = OUI.serialOUI;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema")
    private Long idSistema;

    @Column(name = "nombre_sistema")
    private String nombreSistema;

    @Column(name = "version")
    private String version;

    @Column(name = "codigo_sistema")
    private String codigoSistema;
  

    public AdmSistema() {
    }

    /**
     * @return the idSistema
     */
    public Long getIdSistema() {
        return idSistema;
    }

    /**
     * @param idSistema the idSistema to set
     */
    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
    }
    

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the codigoSistema
     */
    public String getCodigoSistema() {
        return codigoSistema;
    }

    /**
     * @param codigoSistema the codigoSistema to set
     */
    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }
    

    /**
     * @return the nombreSistema
     */
    public String getNombreSistema() {
        return nombreSistema;
    }

    /**
     * @param nombreSistema the nombreSistema to set
     */
    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

}
