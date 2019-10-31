/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.repo.adm;

import com.bap.adm.model.adm.AdmUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface AdmUsuarioRepo extends JpaRepository<AdmUsuario, Long> {

    @Query("select o from AdmUsuario o where o.login = :login and o.password = :password")
    List<AdmUsuario> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    AdmUsuario findOneByLogin(String login);
}
