/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.par;

import com.bap.api.model.par.ParMensajeServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ruth
 */
@Repository("parMensajeServicioRepo")
public interface ParMensajeServicioRepo extends JpaRepository<ParMensajeServicio, String> {

    ParMensajeServicio findByCodigo(Long codigo);
}
