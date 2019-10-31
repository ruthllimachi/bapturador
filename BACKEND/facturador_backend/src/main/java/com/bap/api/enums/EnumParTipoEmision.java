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
public enum EnumParTipoEmision {
    ON_LINE(1),
    OFF_LINE(2),
    ON_LINE_MASIVO(3),
    CONTINGENCIA(4);

    private int codigo;

    private EnumParTipoEmision(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
