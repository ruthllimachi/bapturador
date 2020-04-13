package com.bap.api.consumer;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39127.ConsultaEventoSignificativo;
import com.api.fesc_39127.ConsultaEventoSignificativoResponse;
import com.api.fesc_39127.ObjectFactory;
import com.api.fesc_39127.RegistroFinEventoSignificativo;
import com.api.fesc_39127.RegistroFinEventoSignificativoResponse;
import com.api.fesc_39127.RegistroInicioEventoSignificativo;
import com.api.fesc_39127.RegistroInicioEventoSignificativoResponse;
import com.api.fesc_39127.SolicitudConsultaEvento;
import com.api.fesc_39127.SolicitudFinEvento;
import com.api.fesc_39127.SolicitudInicioEvento;
import com.api.fesc_39127.VerificarComunicacion;
import com.api.fesc_39127.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39127;
import com.bap.api.dto.SolicitudCliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@SuppressWarnings("unchecked")
@Component("consumerWS39127")
public class ConsumerWS39127 {

    @Autowired
    @Qualifier("webServiceTemplate39127")
    private WebServiceTemplate webServiceTemplate;

    public int verificarComunicacion() {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            VerificarComunicacion consultaEventoSignificativo = new VerificarComunicacion();
            JAXBElement<VerificarComunicacion> jaxbRequest = objectFactory.createVerificarComunicacion(consultaEventoSignificativo);
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
  

    public Respuesta39127 registroInicioEventoSignificativo(SolicitudCliente solicitud) {

        ObjectFactory objectFactory = new ObjectFactory();
        RegistroInicioEventoSignificativo request = objectFactory.createRegistroInicioEventoSignificativo();
        SolicitudInicioEvento solicitudInicioEvento = new SolicitudInicioEvento();
        solicitudInicioEvento.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudInicioEvento.setCodigoEvento(solicitud.getCodigoEvento());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudInicioEventoCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudInicioEvento.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudInicioEvento.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudInicioEvento.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudInicioEvento.setCufd(solicitud.getApiConfiguracion().getCufd());
        solicitudInicioEvento.setCuis(solicitud.getApiDosificacion().getCuis());
        solicitudInicioEvento.setDescripcion(solicitud.getDescripcion());
        solicitudInicioEvento.setFechaHoraEvento(solicitud.getUtcFechaEnvio());        
        solicitudInicioEvento.setNit(solicitud.getNitEmpresa());
        request.setSolicitudInicioEvento(solicitudInicioEvento);
        JAXBElement<RegistroInicioEventoSignificativo> jaxbRequest = objectFactory.createRegistroInicioEventoSignificativo(request);
        JAXBElement<RegistroInicioEventoSignificativoResponse> registroInicioEventoSignificativoResponse = (JAXBElement<RegistroInicioEventoSignificativoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        RegistroInicioEventoSignificativoResponse response = registroInicioEventoSignificativoResponse.getValue();

        Respuesta39127 respuesta = new Respuesta39127();
        respuesta.setTransaccion(response.getRespuestaListaEventos().isTransaccion());
        respuesta.setCodigoRecepcionEventoSignificativo(response.getRespuestaListaEventos().getCodigoRecepcionEventoSignificativo());
        respuesta.setListaCodigos(response.getRespuestaListaEventos().getListaCodigos());
        respuesta.setListaCodigosRespuestas(response.getRespuestaListaEventos().getListaCodigosRespuestas());
        return respuesta;
    }

    public Respuesta39127 registroFinEventoSignificativo(SolicitudCliente solicitud) {

        ObjectFactory objectFactory = new ObjectFactory();
        
        RegistroFinEventoSignificativo request = objectFactory.createRegistroFinEventoSignificativo();        
        
        SolicitudFinEvento solicitudFinEvento = new SolicitudFinEvento();        
        solicitudFinEvento.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudFinEvento.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudFinEvento.setNit(solicitud.getNitEmpresa());
        solicitudFinEvento.setCuis(solicitud.getApiDosificacion().getCuis());        
        solicitudFinEvento.setCufd(solicitud.getApiConfiguracion().getCufd());
        solicitudFinEvento.setCodigoSucursal(solicitud.getCodigoSucursal());        
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudFinEventoCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudFinEvento.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudFinEvento.setCodigoRecepcionEvento(solicitud.getCodigoRecepcion());        
        solicitudFinEvento.setFechaHoraFinEvento(solicitud.getUtcFechaEnvio());
        
        request.setSolicitudFinEvento(solicitudFinEvento);
        JAXBElement<RegistroFinEventoSignificativo> jaxbRequest = objectFactory.createRegistroFinEventoSignificativo(request);
        JAXBElement<RegistroFinEventoSignificativoResponse> registroFinEventoSignificativoResponse = (JAXBElement<RegistroFinEventoSignificativoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        RegistroFinEventoSignificativoResponse response = registroFinEventoSignificativoResponse.getValue();

        Respuesta39127 respuesta = new Respuesta39127();
        respuesta.setTransaccion(response.getRespuestaListaEventos().isTransaccion());        
        respuesta.setListaCodigos(response.getRespuestaListaEventos().getListaCodigos());
        respuesta.setListaCodigosRespuestas(response.getRespuestaListaEventos().getListaCodigosRespuestas());
        return respuesta;
    }
    
      public Respuesta39127 consultaEventoSignificativo(SolicitudCliente solicitud) {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            ConsultaEventoSignificativo request = objectFactory.createConsultaEventoSignificativo();
            
            SolicitudConsultaEvento solicitudConsultaEvento = new SolicitudConsultaEvento();
            solicitudConsultaEvento.setCodigoAmbiente(solicitud.getCodigoAmbiente());
            solicitudConsultaEvento.setCodigoSistema(solicitud.getCodigoSistema());
            solicitudConsultaEvento.setNit(solicitud.getNitEmpresa());
            solicitudConsultaEvento.setCuis(solicitud.getApiDosificacion().getCuis());
            solicitudConsultaEvento.setCufd(solicitud.getApiConfiguracion().getCufd());
            solicitudConsultaEvento.setCodigoSucursal(solicitud.getCodigoSucursal());
            JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudConsultaEventoCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
            solicitudConsultaEvento.setCodigoPuntoVenta(codigoPuntoVenta);                   
            XMLGregorianCalendar xmlGregoriaFechaEvento = DatatypeFactory.newInstance().newXMLGregorianCalendar(solicitud.getUtcFechaEnvio());
            solicitudConsultaEvento.setFechaEvento(xmlGregoriaFechaEvento);
            
            request.setSolicitudConsultaEvento(solicitudConsultaEvento);
            JAXBElement<ConsultaEventoSignificativo> jaxbRequest = objectFactory.createConsultaEventoSignificativo(request);
            JAXBElement<ConsultaEventoSignificativoResponse> consultaEventoSignificativoResponse = (JAXBElement<ConsultaEventoSignificativoResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            ConsultaEventoSignificativoResponse response = consultaEventoSignificativoResponse.getValue();

            Respuesta39127 respuesta = new Respuesta39127();
            respuesta.setTransaccion(response.getRespuestaListaEventos().isTransaccion());            
            respuesta.setListaCodigos(response.getRespuestaListaEventos().getListaCodigos());
            respuesta.setListaCodigosRespuestas(response.getRespuestaListaEventos().getListaCodigosRespuestas());
            return respuesta;
        } catch (DatatypeConfigurationException ex) {
            System.out.println("error es " + ex.getMessage());
            Logger.getLogger(ConsumerWS39127.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
