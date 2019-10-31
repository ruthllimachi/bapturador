/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.repo.adm;

import com.bap.adm.model.adm.AdmSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface AdmSessionRepo extends JpaRepository<AdmSession, Long> {

    //falta control por fecha de expiracion
    @Query("select o from AdmSession o where  o.admUsuario.idUsuario = :idUsuario")
    List<AdmSession> findUsuario(@Param("idUsuario") Long idUsuario);
}
