package com.bap.api.dto;

import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.model.par.ParEstado;
import com.bap.api.model.par.ParEventoSignificativo;
import com.bap.api.model.par.ParTipoEventoSignificativo;
import com.bap.api.model.par.ParTipoModalidad;
import com.bap.api.model.par.ParTipoPuntoVenta;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;

public class SolicitudCliente {

    private Long idEmpresa;
    private int codigoAmbiente;
    private int codigoAutorizacion;
    private int codigoPuntoVenta;
    private String codigoSistema;
    private int codigoSucursal;
    private long nitEmpresa;
    private String descripcion;
    private int codigoSolicitud;
    private String nombrePuntoVenta;
    private int codigoTipoPuntoVenta;
    private String cufd;    
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaEnvio;
    private String utcFechaEnvio;
    private String grupo;
    private int codigoDocumentoFiscal;
    private int codigoDocumentoSector;
    private int codigoEmision;
    private String archivo;
    private String hashArchivo;
    private Long codigoRecepcion;
    private long numeroDocumentoFiscal;
    private String cuf;
    private int codigoMotivo;
    private int codigoEvento;
    private int codigoModalidad;
    private String cuis;
    private String login;
    private String password;
    private ApiSucursal apiSucursal;
    private ApiPuntoVenta apiPuntoVenta;
    private ParTipoPuntoVenta parTipoPuntoVenta;
    private ParTipoModalidad parTipoModalidad;
    private ApiDosificacion apiDosificacion;
    private ApiConfiguracion apiConfiguracion;
    private ParEstado parEstado;
    private ParEventoSignificativo parEventoSignificativo;
    private ParTipoEventoSignificativo parTipoEventoSignificativo;

    public int getCodigoAmbiente() {
        return codigoAmbiente;
    }

    public void setCodigoAmbiente(int codigoAmbiente) {
        this.codigoAmbiente = codigoAmbiente;
    }

    public int getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(int codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public int getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }

    public void setCodigoPuntoVenta(int codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(int codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }

    public void setNombrePuntoVenta(String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }

    public int getCodigoTipoPuntoVenta() {
        return codigoTipoPuntoVenta;
    }

    public void setCodigoTipoPuntoVenta(int codigoTipoPuntoVenta) {
        this.codigoTipoPuntoVenta = codigoTipoPuntoVenta;
    }

    public String getCufd() {
        return cufd;
    }

    public void setCufd(String cufd) {
        this.cufd = cufd;
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
     * @return the apiSucursal
     */
    public ApiSucursal getApiSucursal() {
        return apiSucursal;
    }

    /**
     * @param apiSucursal the apiSucursal to set
     */
    public void setApiSucursal(ApiSucursal apiSucursal) {
        this.apiSucursal = apiSucursal;
    }

    /**
     * @return the apiPuntoVenta
     */
    public ApiPuntoVenta getApiPuntoVenta() {
        return apiPuntoVenta;
    }

    /**
     * @param apiPuntoVenta the apiPuntoVenta to set
     */
    public void setApiPuntoVenta(ApiPuntoVenta apiPuntoVenta) {
        this.apiPuntoVenta = apiPuntoVenta;
    }

    /**
     * @return the parTipoPuntoVenta
     */
    public ParTipoPuntoVenta getParTipoPuntoVenta() {
        return parTipoPuntoVenta;
    }

    /**
     * @param parTipoPuntoVenta the parTipoPuntoVenta to set
     */
    public void setParTipoPuntoVenta(ParTipoPuntoVenta parTipoPuntoVenta) {
        this.parTipoPuntoVenta = parTipoPuntoVenta;
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
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
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
     * @return the codigoDocumentoFiscal
     */
    public int getCodigoDocumentoFiscal() {
        return codigoDocumentoFiscal;
    }

    /**
     * @param codigoDocumentoFiscal the codigoDocumentoFiscal to set
     */
    public void setCodigoDocumentoFiscal(int codigoDocumentoFiscal) {
        this.codigoDocumentoFiscal = codigoDocumentoFiscal;
    }

    /**
     * @return the codigoDocumentoSector
     */
    public int getCodigoDocumentoSector() {
        return codigoDocumentoSector;
    }

    /**
     * @param codigoDocumentoSector the codigoDocumentoSector to set
     */
    public void setCodigoDocumentoSector(int codigoDocumentoSector) {
        this.codigoDocumentoSector = codigoDocumentoSector;
    }

    /**
     * @return the codigoEmision
     */
    public int getCodigoEmision() {
        return codigoEmision;
    }

    /**
     * @param codigoEmision the codigoEmision to set
     */
    public void setCodigoEmision(int codigoEmision) {
        this.codigoEmision = codigoEmision;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the hashArchivo
     */
    public String getHashArchivo() {
        return hashArchivo;
    }

    /**
     * @param hashArchivo the hashArchivo to set
     */
    public void setHashArchivo(String hashArchivo) {
        this.hashArchivo = hashArchivo;
    }

    /**
     * @return the codigoRecepcion
     */
    public Long getCodigoRecepcion() {
        return codigoRecepcion;
    }

    /**
     * @param codigoRecepcion the codigoRecepcion to set
     */
    public void setCodigoRecepcion(Long codigoRecepcion) {
        this.codigoRecepcion = codigoRecepcion;
    }

    /**
     * @return the numeroDocumentoFiscal
     */
    public long getNumeroDocumentoFiscal() {
        return numeroDocumentoFiscal;
    }

    /**
     * @param numeroDocumentoFiscal the numeroDocumentoFiscal to set
     */
    public void setNumeroDocumentoFiscal(long numeroDocumentoFiscal) {
        this.numeroDocumentoFiscal = numeroDocumentoFiscal;
    }

    /**
     * @return the cuf
     */
    public String getCuf() {
        return cuf;
    }

    /**
     * @param cuf the cuf to set
     */
    public void setCuf(String cuf) {
        this.cuf = cuf;
    }

    /**
     * @return the codigoMotivo
     */
    public int getCodigoMotivo() {
        return codigoMotivo;
    }

    /**
     * @param codigoMotivo the codigoMotivo to set
     */
    public void setCodigoMotivo(int codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    /**
     * @return the apiDosificacion
     */
    public ApiDosificacion getApiDosificacion() {
        return apiDosificacion;
    }

    /**
     * @param apiDosificacion the apiDosificacion to set
     */
    public void setApiDosificacion(ApiDosificacion apiDosificacion) {
        this.apiDosificacion = apiDosificacion;
    }

    /**
     * @return the apiConfiguracion
     */
    public ApiConfiguracion getApiConfiguracion() {
        return apiConfiguracion;
    }

    /**
     * @param apiConfiguracion the apiConfiguracion to set
     */
    public void setApiConfiguracion(ApiConfiguracion apiConfiguracion) {
        this.apiConfiguracion = apiConfiguracion;
    }   
   

    /**
     * @return the codigoEvento
     */
    public int getCodigoEvento() {
        return codigoEvento;
    }

    /**
     * @param codigoEvento the codigoEvento to set
     */
    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }
  
    /**
     * @return the utcFechaEnvio
     */
    public String getUtcFechaEnvio() {
        return utcFechaEnvio;
    }

    /**
     * @param utcFechaEnvio the utcFechaEnvio to set
     */
    public void setUtcFechaEnvio(String utcFechaEnvio) {
        this.utcFechaEnvio = utcFechaEnvio;
    }

    /**
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * @return the fechaEnvio
     */
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @return the parEstado
     */
    public ParEstado getParEstado() {
        return parEstado;
    }

    /**
     * @param parEstado the parEstado to set
     */
    public void setParEstado(ParEstado parEstado) {
        this.parEstado = parEstado;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the codigoModalidad
     */
    public int getCodigoModalidad() {
        return codigoModalidad;
    }

    /**
     * @param codigoModalidad the codigoModalidad to set
     */
    public void setCodigoModalidad(int codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
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
     * @return the parEventoSignificativo
     */
    public ParEventoSignificativo getParEventoSignificativo() {
        return parEventoSignificativo;
    }

    /**
     * @param parEventoSignificativo the parEventoSignificativo to set
     */
    public void setParEventoSignificativo(ParEventoSignificativo parEventoSignificativo) {
        this.parEventoSignificativo = parEventoSignificativo;
    }

    /**
     * @return the parTipoEventoSignificativo
     */
    public ParTipoEventoSignificativo getParTipoEventoSignificativo() {
        return parTipoEventoSignificativo;
    }

    /**
     * @param parTipoEventoSignificativo the parTipoEventoSignificativo to set
     */
    public void setParTipoEventoSignificativo(ParTipoEventoSignificativo parTipoEventoSignificativo) {
        this.parTipoEventoSignificativo = parTipoEventoSignificativo;
    }

}
