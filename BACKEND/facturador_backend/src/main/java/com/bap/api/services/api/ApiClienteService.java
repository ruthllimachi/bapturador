/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiCliente;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author ruth
 */
public interface ApiClienteService extends AbstractEntity<ApiCliente> {

    ApiCliente leerPorIdEmpresaAndCodigoCliente(Long idEmpresa, String codigoCliente);

    List<ApiCliente> listarClientePorIdEmpresa(Long idEmpresa);

    void cargarClientes(InputStream fileInputStream) throws Exception;

//    ApiCliente checkIfThereCustomerByNit(Long nit) throws Exception;
    
    ApiCliente checkIfThereCustomerByDoc(String numeroDocumento) throws Exception;

}
