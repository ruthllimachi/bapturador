/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.api;

import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.par.ParMensajeServicio;
import java.util.List;

/**
 *
 * @author ruth //
 */
public interface ApiHistoricoService {

    void registraHistorico(ApiFactura apiFactura, List<ParMensajeServicio> listaParMensajeServicio);

}
