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
public enum EnumParEstado {
    ESTADO_VIGENTE("VGTE"),
    ESTADO_NO_VIGENTE("NVGTE");
    
    private String codigo;

    private EnumParEstado(String codigo) {
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
