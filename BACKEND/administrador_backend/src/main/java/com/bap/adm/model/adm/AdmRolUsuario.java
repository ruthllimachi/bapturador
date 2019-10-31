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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "adm_rol_usuario")
public class AdmRolUsuario implements Serializable {

     private static final long serialVersion = OUI.serialOUI;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_usuario")
    private Long idRolUsuario;

    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private AdmRol admRol;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private AdmUsuario admUsuario;

    public AdmRolUsuario() {
    }

    public Long getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Long idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
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

    /**
     * @return the admUsuario
     */
    public AdmUsuario getAdmUsuario() {
        return admUsuario;
    }

    /**
     * @param admUsuario the admUsuario to set
     */
    public void setAdmUsuario(AdmUsuario admUsuario) {
        this.admUsuario = admUsuario;
    }

}
