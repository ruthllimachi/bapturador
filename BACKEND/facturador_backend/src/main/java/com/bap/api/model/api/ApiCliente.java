/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParTipoCliente;
import com.bap.api.model.par.ParTipoDocumentoIdentidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_cliente")
public class ApiCliente extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Size(max = 100, message = "Direccion no debe ser mayor a 100 caracteres")
    @Column(name = "codigo_cliente")
    private String codigoCliente;

    @Size(max = 200, message = "Direccion no debe ser mayor a 200 caracteres")
    @Column(name = "nombre_razon_social",nullable = false, length = 200)
    private String nombreRazonSocial;  

    @ManyToOne()
    @JoinColumn(name = "tipo_documento_identidad", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoIdentidad parTipoDocumentoIdentidad;

    @Size(max = 20, message = "Numero de Documento no debe ser mayor a 20 caracteres")
    @Column(name = "numero_documento")
    private String numeroDocumento;
    
    @Column(name = "complemento")
    private String complemento;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @ManyToOne()
    @JoinColumn(name = "tipo_cliente", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoCliente parTipoCliente;
    
    @Transient
    private String empresa;
    
    @Transient
    private Long tipoDocumento;
    
    @Transient
    private String tipoCliente;
    
    @Transient
    private String usuarioAux;

    /**
     * @return the idCliente
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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
     * @return the codigoCliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
 

    /**
     * @return the parTipoDocumentoIdentidad
     */
    public ParTipoDocumentoIdentidad getParTipoDocumentoIdentidad() {
        return parTipoDocumentoIdentidad;
    }

    /**
     * @param parTipoDocumentoIdentidad the parTipoDocumentoIdentidad to set
     */
    public void setParTipoDocumentoIdentidad(ParTipoDocumentoIdentidad parTipoDocumentoIdentidad) {
        this.parTipoDocumentoIdentidad = parTipoDocumentoIdentidad;
    }

   

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the parTipoCliente
     */
    public ParTipoCliente getParTipoCliente() {
        return parTipoCliente;
    }

    /**
     * @param parTipoCliente the parTipoCliente to set
     */
    public void setParTipoCliente(ParTipoCliente parTipoCliente) {
        this.parTipoCliente = parTipoCliente;
    }
   

    /**
     * @return the nombreRazonSocial
     */
    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    /**
     * @param nombreRazonSocial the nombreRazonSocial to set
     */
    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Long getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Long tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getUsuarioAux() {
        return usuarioAux;
    }

    public void setUsuarioAux(String usuarioAux) {
        this.usuarioAux = usuarioAux;
    }
    
}
