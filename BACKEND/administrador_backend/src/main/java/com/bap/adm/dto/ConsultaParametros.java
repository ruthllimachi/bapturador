/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.dto;

public class ConsultaParametros {
    
    private Long idEmpresa;
    private Long idUsuario;
    private Long idSucursal;
    private Long idPuntoVenta;
    private int codigoAmbiente;
    private long nitEmpresa;
    private String codigoSistema;
    private String razonSocial;
    private int tipoModalidadDefecto;
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
     * @return the nitEmpresa
     */
    public long getNitEmpresa() {
        return nitEmpresa;
    }

    /**
     * @param nitEmpresa the nitEmpresa to set
     */
    public void setNitEmpresa(long nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the tipoModalidadDefecto
     */
    public int getTipoModalidadDefecto() {
        return tipoModalidadDefecto;
    }

    /**
     * @param tipoModalidadDefecto the tipoModalidadDefecto to set
     */
    public void setTipoModalidadDefecto(int tipoModalidadDefecto) {
        this.tipoModalidadDefecto = tipoModalidadDefecto;
    }

}
