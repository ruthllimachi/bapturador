/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ruth
 */
public interface ApiHistoricoRepo extends JpaRepository<ApiHistorico, Long> {

}
