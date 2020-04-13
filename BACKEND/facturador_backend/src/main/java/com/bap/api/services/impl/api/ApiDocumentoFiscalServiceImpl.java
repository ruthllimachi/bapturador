/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.DocumentoFiscalDTO;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.RespuestaDocumentoFiscal;
import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.services.api.ApiDocumentoFiscalService;
import com.bap.api.services.api.ApiFacturaService;
import com.bap.api.services.par.ParEstadoDocumentoService;
import com.bap.api.services.par.ParMensajeFacturadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiDocumentoFiscalServiceImpl implements ApiDocumentoFiscalService {
    
    @Autowired
    ApiFacturaService apiFacturaService;
    
    @Autowired
    ParEstadoDocumentoService parEstadoDocumentoService;
    
    @Autowired
    ParMensajeFacturadorService parMensajeFacturadorService;
    
    @Override
    public Respuesta registraFacturaEstandar(DocumentoFiscalDTO documentoFiscalDTO) {
        Respuesta respuesta = null;
        RespuestaDocumentoFiscal respuestaDocumentoFiscal = apiFacturaService.validaFacturaEstandar(documentoFiscalDTO);
        if (respuestaDocumentoFiscal.isTransaccion()) {
            ApiFactura apiFactura = respuestaDocumentoFiscal.getApiFactura();
            respuesta = apiFacturaService.verficaComunicacion(apiFactura.getApiDosificacion().getParTipoModalidad());
            if (respuesta.isTransaccion()) {
                respuesta = apiFacturaService.enviaFacturaEstandar(apiFactura, respuestaDocumentoFiscal.getSolicitud(), documentoFiscalDTO.getCabeceraDTO());
                if (respuesta.isTransaccion()) {
                    ParMensajeFacturador parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("37");
                    respuesta = new Respuesta();
                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                }
            }
        } else {
            respuesta = new Respuesta();
            respuesta.setParMensajeFacturador(respuestaDocumentoFiscal.getParMensajeFacturador());
        }
        return respuesta;
    }

}
