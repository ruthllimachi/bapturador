/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.enums;

/**
 *
 * @author ruth
 */
//    DOCUMENTO_EVENTO("EVENT"),
//    DOCUMENTO_ADMITIDO("ADMTD"),
//    DOCUMENTO_ERROR("ERROR");
//
public enum EnumParEstadoDocumento {
    DOCUMENTO_VALIDO("VLIDA"),
    DOCUMENTO_ANULADO("ANLDA"),
    DOCUMENTO_RECEPCIONADA("RCNDA");

    
    private String codigo;

    private EnumParEstadoDocumento(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
