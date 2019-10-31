/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.model.adm;

import com.bap.adm.configurate.OUI;
import com.bap.adm.model.par.ParEstadoUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "adm_usuario")
public class AdmUsuario implements Serializable {

    private static final long serialVersion = OUI.serialOUI;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private AdmPersona admPersona;

    @ManyToOne()
    @JoinColumn(name = "estado_usuario", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoUsuario parEstadoUsuario;

    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "id_punto_venta")
    private Long idPuntoVenta;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    /**
     * @return the admPersona
     */
    public AdmPersona getAdmPersona() {
        return admPersona;
    }

    /**
     * @param admPersona the admPersona to set
     */
    public void setAdmPersona(AdmPersona admPersona) {
        this.admPersona = admPersona;
    }

    /**
     * @return the parEstadoUsuario
     */
    public ParEstadoUsuario getParEstadoUsuario() {
        return parEstadoUsuario;
    }

    /**
     * @param parEstadoUsuario the parEstadoUsuario to set
     */
    public void setParEstadoUsuario(ParEstadoUsuario parEstadoUsuario) {
        this.parEstadoUsuario = parEstadoUsuario;
    }

    /**
     * @return the idSucursal
     */
    public Long getIdSucursal() {
        return idSucursal;
    }

    /**
     * @param idSucursal the idSucursal to set
     */
    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * @return the idPuntoVenta
     */
    public Long getIdPuntoVenta() {
        return idPuntoVenta;
    }

    /**
     * @param idPuntoVenta the idPuntoVenta to set
     */
    public void setIdPuntoVenta(Long idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }

  

}
