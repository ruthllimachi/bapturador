package com.bap.api.consumer;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39127.ConsultaEventoSignificativo;
import com.api.fesc_39127.ConsultaEventoSignificativoResponse;
import com.api.fesc_39127.ObjectFactory;
import com.api.fesc_39127.RespuestaCodigosMensajesSoapDto;
import com.api.fesc_39127.VerificarComunicacion;
import com.api.fesc_39127.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;

@SuppressWarnings("unchecked")
@Component("consumerWS39127")
public class ConsumerWS39127 {

    @Autowired
    @Qualifier("webServiceTemplate39127")
    private WebServiceTemplate webServiceTemplate;

    public int verificarComunicacion() {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            VerificarComunicacion request = new VerificarComunicacion();
            JAXBElement<VerificarComunicacion> jaxbRequest = objectFactory.createVerificarComunicacion(request);
            JAXBElement<VerificarComunicacionResponse> response = (JAXBElement<VerificarComunicacionResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            VerificarComunicacionResponse verificarComunicacionResponse = response.getValue();
            int codigo = verificarComunicacionResponse.getReturn();
            return codigo;
        } catch (Exception e) {
            System.out.println("#####Error " + e.getMessage());
            return -1;
        }
    }

    public Respuesta39117 consultaEventoSignificativo(SolicitudCliente solicitudSincronizacion) {
        ObjectFactory objectFactory = new ObjectFactory();
        ConsultaEventoSignificativo request = objectFactory.createConsultaEventoSignificativo();
        request.getSolicitudConsultaEvento().setCodigoAmbiente(solicitudSincronizacion.getCodigoAmbiente());
//		request.getSolicitudConsultaEvento().setCodigoPuntoVenta(value);		
        request.getSolicitudConsultaEvento().setCodigoSistema(solicitudSincronizacion.getCodigoSistema());
        request.getSolicitudConsultaEvento().setCodigoSucursal(solicitudSincronizacion.getCodigoSucursal());
        request.getSolicitudConsultaEvento().setCufd(solicitudSincronizacion.getCufd());
        //request.getSolicitudConsultaEvento().setFechaEvento(solicitudSincronizacion.getFechaEvento());		
        request.getSolicitudConsultaEvento().setNit(solicitudSincronizacion.getNitEmpresa());

        JAXBElement<ConsultaEventoSignificativo> jaxbRequest = objectFactory.createConsultaEventoSignificativo(request);
        JAXBElement<ConsultaEventoSignificativoResponse> consultaEventoSignificativoResponse = (JAXBElement<ConsultaEventoSignificativoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ConsultaEventoSignificativoResponse response = consultaEventoSignificativoResponse.getValue();

        List<RespuestaCodigosMensajesSoapDto> listMensajeSoapDto = response.getRespuestaListaEventos()
                .getListaCodigosRespuestas();

        return null;
    }

}
