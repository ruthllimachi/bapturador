/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParEstado;
import com.bap.api.model.par.ParTipoModalidad;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDate;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "api_dosificacion")
public class ApiDosificacion extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dosificacion")
    private Long idDosificacion;

    @Column(name = "cuis")
    private String cuis;

    @ManyToOne()
    @JoinColumn(name = "tipo_modalidad", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoModalidad parTipoModalidad;

    @ManyToOne()
    @JoinColumn(name = "estado_dosificacion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstado estadoDosificacion;

    @Column(name = "directorio_certificado")
    private String directorioCertificado;

    @Column(name = "archivo_clave_privada")
    private String archivoClavePrivada;

    @Column(name = "archivo_certificado")
    private String archivoCertificado;

    @Column(name = "directorio_xml")
    private String directorioXml;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;

    /**
     * @return the idDosificacion
     */
    public Long getIdDosificacion() {
        return idDosificacion;
    }

    /**
     * @param idDosificacion the idDosificacion to set
     */
    public void setIdDosificacion(Long idDosificacion) {
        this.idDosificacion = idDosificacion;
    }

    /**
     * @return the cuis
     */
    public String getCuis() {
        return cuis;
    }

    /**
     * @param cuis the cuis to set
     */
    public void setCuis(String cuis) {
        this.cuis = cuis;
    }

    /**
     * @return the parTipoModalidad
     */
    public ParTipoModalidad getParTipoModalidad() {
        return parTipoModalidad;
    }

    /**
     * @param parTipoModalidad the parTipoModalidad to set
     */
    public void setParTipoModalidad(ParTipoModalidad parTipoModalidad) {
        this.parTipoModalidad = parTipoModalidad;
    }

    /**
     * @return the estadoDosificacion
     */
    public ParEstado getEstadoDosificacion() {
        return estadoDosificacion;
    }

    /**
     * @param estadoDosificacion the estadoDosificacion to set
     */
    public void setEstadoDosificacion(ParEstado estadoDosificacion) {
        this.estadoDosificacion = estadoDosificacion;
    }

    /**
     * @return the directorioCertificado
     */
    public String getDirectorioCertificado() {
        return directorioCertificado;
    }

    /**
     * @param directorioCertificado the directorioCertificado to set
     */
    public void setDirectorioCertificado(String directorioCertificado) {
        this.directorioCertificado = directorioCertificado;
    }

    /**
     * @return the archivoClavePrivada
     */
    public String getArchivoClavePrivada() {
        return archivoClavePrivada;
    }

    /**
     * @param archivoClavePrivada the archivoClavePrivada to set
     */
    public void setArchivoClavePrivada(String archivoClavePrivada) {
        this.archivoClavePrivada = archivoClavePrivada;
    }

    /**
     * @return the archivoCertificado
     */
    public String getArchivoCertificado() {
        return archivoCertificado;
    }

    /**
     * @param archivoCertificado the archivoCertificado to set
     */
    public void setArchivoCertificado(String archivoCertificado) {
        this.archivoCertificado = archivoCertificado;
    }    

    /**
     * @return the fechaVigencia
     */
    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * @param fechaVigencia the fechaVigencia to set
     */
    public void setFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * @return the directorioXml
     */
    public String getDirectorioXml() {
        return directorioXml;
    }

    /**
     * @param directorioXml the directorioXml to set
     */
    public void setDirectorioXml(String directorioXml) {
        this.directorioXml = directorioXml;
    }

}
