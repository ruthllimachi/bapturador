/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.api.ApiHistorico;
import com.bap.api.model.api.ApiHistoricoDetalle;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.api.ApiHistoricoRepo;
import com.bap.api.services.api.ApiHistoricoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiHistoricoServiceImpl implements ApiHistoricoService {

    @Autowired
    private ApiHistoricoRepo repo;

    private ApiHistorico registrar(ApiHistorico t) {
        t.getApiHistoricoDetalle().forEach(det -> {
            det.setApiHistorico(t);
        });
        return repo.save(t);
    }

    @Override
    public void registraHistorico(ApiFactura apiFactura, List<ParMensajeServicio> listaParMensajeServicio) {
        ApiHistorico t = new ApiHistorico();
        t.setNitEmisor(apiFactura.getNumeroFactura());
        t.setCuf(apiFactura.getCuf());
        t.setNumeroFactura(apiFactura.getNumeroFactura());
        t.setFechaAlta(new Date());
        t.setUsuarioAlta(apiFactura.getUsuario());
        t.setUtcFechaEmision(apiFactura.getUtcFechaEmision());
        t.setIdEmpresaDocumento(apiFactura.getApiEmpresaDocumento().getIdEmpresaDocumento());
        if (apiFactura.getApiPuntoVenta() == null) {
            t.setIdSucursal(apiFactura.getApiSucursal().getIdSucursal());
        } else {
            t.setIdPuntoVenta(apiFactura.getApiPuntoVenta().getIdPuntoVenta());
        }
        ApiHistoricoDetalle apiHistoricoDetalle;
        List<ApiHistoricoDetalle> lista = new ArrayList<>();
        for (ParMensajeServicio parMensajeServicio : listaParMensajeServicio) {
            apiHistoricoDetalle = new ApiHistoricoDetalle();
            apiHistoricoDetalle.setCodigoMensaje(parMensajeServicio.getCodigo());
            apiHistoricoDetalle.setMensaje(parMensajeServicio.getDescripcion());
            lista.add(apiHistoricoDetalle);
        }
        t.setApiHistoricoDetalle(lista);
        registrar(t);
    }

}
