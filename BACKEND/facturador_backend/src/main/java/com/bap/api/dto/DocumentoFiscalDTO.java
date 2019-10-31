/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.dto;

import java.util.List;

/**
 *
 * @author ruth
 */
public class DocumentoFiscalDTO {

    private CabeceraDTO cabeceraDTO;
    private List<DetalleDTO> listaDetalleDTO;
    private boolean valido;

    /**
     * @return the cabeceraDTO
     */
    public CabeceraDTO getCabeceraDTO() {
        return cabeceraDTO;
    }

    /**
     * @param cabeceraDTO the cabeceraDTO to set
     */
    public void setCabeceraDTO(CabeceraDTO cabeceraDTO) {
        this.cabeceraDTO = cabeceraDTO;
    }

    /**
     * @return the listaDetalleDTO
     */
    public List<DetalleDTO> getListaDetalleDTO() {
        return listaDetalleDTO;
    }

    /**
     * @param listaDetalleDTO the listaDetalleDTO to set
     */
    public void setListaDetalleDTO(List<DetalleDTO> listaDetalleDTO) {
        this.listaDetalleDTO = listaDetalleDTO;
    }

    /**
     * @return the valido
     */
    public boolean isValido() {
        return valido;
    }

    /**
     * @param valido the valido to set
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }

}
