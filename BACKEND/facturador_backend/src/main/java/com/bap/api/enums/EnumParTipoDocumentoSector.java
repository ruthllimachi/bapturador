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
public enum EnumParTipoDocumentoSector {
    FACTURA_ESTANDAR(1),
    FACTURA_COMERCIAL_EXPORTACION(12);

    private int codigo;

    private EnumParTipoDocumentoSector(int codigo) {
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
