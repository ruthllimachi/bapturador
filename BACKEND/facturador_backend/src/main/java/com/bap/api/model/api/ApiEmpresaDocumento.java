/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.model.par.ParTipoDocumentoFiscal;
import com.bap.api.model.par.ParTipoDocumentoSector;
import com.bap.api.model.par.ParTipoModalidad;

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
@Table(name = "api_empresa_documento")
public class ApiEmpresaDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa_documento")
    private Long idEmpresaDocumento;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @ManyToOne()
    @JoinColumn(name = "tipo_documento_fiscal", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoFiscal parTipoDocumentoFiscal;

    @ManyToOne()
    @JoinColumn(name = "tipo_documento_sector", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoSector parTipoDocumentoSector;

    @ManyToOne()
    @JoinColumn(name = "tipo_modalidad", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoModalidad parTipoModalidad;

    /**
     * @return the idEmpresaDocumento
     */
    public Long getIdEmpresaDocumento() {
        return idEmpresaDocumento;
    }

    /**
     * @param idEmpresaDocumento the idEmpresaDocumento to set
     */
    public void setIdEmpresaDocumento(Long idEmpresaDocumento) {
        this.idEmpresaDocumento = idEmpresaDocumento;
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
     * @return the parTipoDocumentoSector
     */
    public ParTipoDocumentoSector getParTipoDocumentoSector() {
        return parTipoDocumentoSector;
    }

    /**
     * @param parTipoDocumentoSector the parTipoDocumentoSector to set
     */
    public void setParTipoDocumentoSector(ParTipoDocumentoSector parTipoDocumentoSector) {
        this.parTipoDocumentoSector = parTipoDocumentoSector;
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
     * @return the parTipoDocumentoFiscal
     */
    public ParTipoDocumentoFiscal getParTipoDocumentoFiscal() {
        return parTipoDocumentoFiscal;
    }

    /**
     * @param parTipoDocumentoFiscal the parTipoDocumentoFiscal to set
     */
    public void setParTipoDocumentoFiscal(ParTipoDocumentoFiscal parTipoDocumentoFiscal) {
        this.parTipoDocumentoFiscal = parTipoDocumentoFiscal;
    }

}
