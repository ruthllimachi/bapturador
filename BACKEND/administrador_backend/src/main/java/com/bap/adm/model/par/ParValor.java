package com.bap.adm.model.par;


import com.bap.adm.configurate.OUI;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "par_valor")
public class ParValor implements Serializable {

    private static final long serialVersion = OUI.serialOUI;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_par_valor")
    private Integer idParValor;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "contexto")
    private String contexto;

    @Column(name = "descripcion")
    private String descripcion;

    public ParValor() {
    }

    /**
     * @return the idParValor
     */
    public Integer getIdParValor() {
        return idParValor;
    }

    /**
     * @param idParValor the idParValor to set
     */
    public void setIdParValor(Integer idParValor) {
        this.idParValor = idParValor;
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

    /**
     * @return the contexto
     */
    public String getContexto() {
        return contexto;
    }

    /**
     * @param contexto the contexto to set
     */
    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
