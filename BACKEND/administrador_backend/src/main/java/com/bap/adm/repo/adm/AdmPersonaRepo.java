/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.repo.adm;

import com.bap.adm.model.adm.AdmPersona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ruth
 */
public interface AdmPersonaRepo extends JpaRepository<AdmPersona, Long> {
    
}
