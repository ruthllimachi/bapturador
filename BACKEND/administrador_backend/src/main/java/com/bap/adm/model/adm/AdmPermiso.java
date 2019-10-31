/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.model.adm;

import com.bap.adm.configurate.OUI;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "adm_permiso")
public class AdmPermiso implements Serializable {

     private static final long serialVersion = OUI.serialOUI;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permiso")
    private Long idPermiso;
    
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private AdmProceso admProceso;
    
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private AdmRol admRol;

    public AdmPermiso() {
    }

    public AdmPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    /**
     * @return the idPermiso
     */
    public Long getIdPermiso() {
        return idPermiso;
    }

    /**
     * @param idPermiso the idPermiso to set
     */
    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    /**
     * @return the admProceso
     */
    public AdmProceso getAdmProceso() {
        return admProceso;
    }

    /**
     * @param admProceso the admProceso to set
     */
    public void setAdmProceso(AdmProceso admProceso) {
        this.admProceso = admProceso;
    }

    /**
     * @return the admRol
     */
    public AdmRol getAdmRol() {
        return admRol;
    }

    /**
     * @param admRol the admRol to set
     */
    public void setAdmRol(AdmRol admRol) {
        this.admRol = admRol;
    }

    
}
