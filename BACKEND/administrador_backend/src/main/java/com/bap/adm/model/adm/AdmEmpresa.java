/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.model.adm;

import com.bap.adm.configurate.OUI;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "adm_empresa")
public class AdmEmpresa implements Serializable {

    private static final long serialVersion = OUI.serialOUI;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "direccion_fiscal")
    private String direccionFiscal;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nit_empresa")
    private Long nitEmpresa;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "telefono_empresa")
    private int telefonoEmpresa;

    @Column(name = "inicio_periodo")
    @Temporal(TemporalType.DATE)
    private Date inicioPeriodo;

    @Column(name = "fin_periodo")
    @Temporal(TemporalType.DATE)
    private Date finPeriodo;

    @Column(name = "codigo_ambiente")
    private int codigoAmbiente;

    public AdmEmpresa() {
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(int telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    /**
     * @return the direccionFiscal
     */
    public String getDireccionFiscal() {
        return direccionFiscal;
    }

    /**
     * @param direccionFiscal the direccionFiscal to set
     */
    public void setDireccionFiscal(String direccionFiscal) {
        this.direccionFiscal = direccionFiscal;
    }

    /**
     * @return the inicioPeriodo
     */
    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    /**
     * @param inicioPeriodo the inicioPeriodo to set
     */
    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    /**
     * @return the finPeriodo
     */
    public Date getFinPeriodo() {
        return finPeriodo;
    }

    /**
     * @param finPeriodo the finPeriodo to set
     */
    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }     

    /**
     * @return the codigoAmbiente
     */
    public int getCodigoAmbiente() {
        return codigoAmbiente;
    }

    /**
     * @param codigoAmbiente the codigoAmbiente to set
     */
    public void setCodigoAmbiente(int codigoAmbiente) {
        this.codigoAmbiente = codigoAmbiente;
    }

    /**
     * @return the nitEmpresa
     */
    public Long getNitEmpresa() {
        return nitEmpresa;
    }

    /**
     * @param nitEmpresa the nitEmpresa to set
     */
    public void setNitEmpresa(Long nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    

}
