/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.configurate.AbstractEntity;
import com.bap.api.model.api.ApiEmpresaDocumento;

/**
 *
 * @author ruth
 */
public interface ApiEmpresaDocumentoService extends AbstractEntity<ApiEmpresaDocumento> {

   ApiEmpresaDocumento leerPotEmpresaAndTipo(Long idEmpresa, Long codigoDocumentoFiscal, Long codigoDocumentoSector, Long codigoTipoModalidad);
}
