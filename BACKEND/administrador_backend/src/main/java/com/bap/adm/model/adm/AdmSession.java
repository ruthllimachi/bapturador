/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.model.adm;

import com.bap.adm.configurate.AbstractAudit;
import com.bap.adm.configurate.OUI;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "adm_session")
public class AdmSession extends AbstractAudit implements Serializable {

    private static final long serialVersion = OUI.serialOUI;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Long idSession;

    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;

    @Column(name = "token")
    private String token;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private AdmUsuario admUsuario;

    public AdmSession() {
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
