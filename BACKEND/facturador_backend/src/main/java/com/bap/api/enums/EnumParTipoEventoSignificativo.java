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
public enum EnumParTipoEventoSignificativo {
    EVENTO_SIGNIFICATIVO_INFORMATIVO("1"),
    EVENTO_SIGNIFICATIVO_CONTINGENCIA("2");

    private String codigo;

    private EnumParTipoEventoSignificativo(String codigo) {
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
