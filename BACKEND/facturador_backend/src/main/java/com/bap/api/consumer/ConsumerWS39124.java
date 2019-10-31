package com.bap.api.consumer;

import com.api.fesc_39124.AnulacionFacturaElectronicaComercialExportacion;
import com.api.fesc_39124.AnulacionFacturaElectronicaComercialExportacionResponse;
import com.api.fesc_39124.ObjectFactory;
import com.api.fesc_39124.RecepcionFacturaElectronicaComercialExportacion;
import com.api.fesc_39124.RecepcionFacturaElectronicaComercialExportacionResponse;
import com.api.fesc_39124.SolicitudAnulacion;
import com.api.fesc_39124.SolicitudRecepcion;
import com.api.fesc_39124.SolicitudValidacionAnulacion;
import com.api.fesc_39124.SolicitudValidacionRecepcion;
import com.api.fesc_39124.ValidacionAnulacionFacturaElectronicaComercialExportacion;
import com.api.fesc_39124.ValidacionAnulacionFacturaElectronicaComercialExportacionResponse;
import com.api.fesc_39124.ValidacionRecepcionFacturaElectronicaComercialExportacion;
import com.api.fesc_39124.ValidacionRecepcionFacturaElectronicaComercialExportacionResponse;
import com.api.fesc_39124.VerificarComunicacion;
import com.api.fesc_39124.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39124;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;


import com.bap.api.dto.SolicitudCliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

//FacturaElectronicaComercialExportacion
@SuppressWarnings("unchecked")
@Component("consumerWS39124")
public class ConsumerWS39124 {

    @Autowired
    @Qualifier("webServiceTemplate39124")
    private WebServiceTemplate webServiceTemplate;

    public int verificaComunicacion() {
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
    
    public Respuesta39124 recepcionFacturaElectronicaComercialExportacion(SolicitudCliente solicitud) {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            RecepcionFacturaElectronicaComercialExportacion request = objectFactory.createRecepcionFacturaElectronicaComercialExportacion();
            SolicitudRecepcion solicitudRecepcion = new SolicitudRecepcion();
            solicitudRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
            solicitudRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());            
            solicitudRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
            solicitudRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
            solicitudRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
            JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
            solicitudRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
            solicitudRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
            solicitudRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
            solicitudRecepcion.setCufd(solicitud.getCufd());
            solicitudRecepcion.setCuis(solicitud.getCuis());
            XMLGregorianCalendar xmlGregoriaFechaEmision = DatatypeFactory.newInstance().newXMLGregorianCalendar(solicitud.getFechaEnvio().toString());
            solicitudRecepcion.setFechaEnvio(xmlGregoriaFechaEmision);
            solicitudRecepcion.setNit(solicitud.getNitEmpresa());
            solicitudRecepcion.setArchivo(solicitud.getArchivo());
            solicitudRecepcion.setHashArchivo(solicitud.getHashArchivo());
            request.setSolicitudServicioRecepcion(solicitudRecepcion);

            JAXBElement<RecepcionFacturaElectronicaComercialExportacion> jaxbRequest = objectFactory.createRecepcionFacturaElectronicaComercialExportacion(request);
            JAXBElement<RecepcionFacturaElectronicaComercialExportacionResponse> recepcionFacturaElectronicaComercialExportacionResponse = (JAXBElement<RecepcionFacturaElectronicaComercialExportacionResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            RecepcionFacturaElectronicaComercialExportacionResponse response = recepcionFacturaElectronicaComercialExportacionResponse.getValue();

            Respuesta39124 respuesta = new Respuesta39124();
            respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
            respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
            respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
            respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
            respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
            respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
            return respuesta;

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ConsumerWS39112.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Respuesta39124 validacionRecepcionFacturaElectronicaComercialExportacion(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionRecepcionFacturaElectronicaComercialExportacion request = objectFactory.createValidacionRecepcionFacturaElectronicaComercialExportacion();

        SolicitudValidacionRecepcion solicitudValidacionRecepcion = new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
        solicitudValidacionRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionRecepcion.setNit(solicitud.getNitEmpresa());
        solicitudValidacionRecepcion.setCuis(solicitud.getCuis());
        solicitudValidacionRecepcion.setCufd(solicitud.getCufd());
        solicitudValidacionRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        
        solicitudValidacionRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionRecepcion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        request.setSolicitudServicioValidacionRecepcion(solicitudValidacionRecepcion);

        JAXBElement<ValidacionRecepcionFacturaElectronicaComercialExportacion> jaxbRequest = objectFactory.createValidacionRecepcionFacturaElectronicaComercialExportacion(request);

        JAXBElement<ValidacionRecepcionFacturaElectronicaComercialExportacionResponse> validacionRecepcionFacturaElectronicaComercialExportacionResponse = (JAXBElement<ValidacionRecepcionFacturaElectronicaComercialExportacionResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ValidacionRecepcionFacturaElectronicaComercialExportacionResponse response = validacionRecepcionFacturaElectronicaComercialExportacionResponse.getValue();

        Respuesta39124 respuesta = new Respuesta39124();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }
    
      public Respuesta39124 anulaFacturaElectronicaComercialExportacion(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        AnulacionFacturaElectronicaComercialExportacion request = objectFactory.createAnulacionFacturaElectronicaComercialExportacion();
        SolicitudAnulacion solicitudAnulacion = new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
        solicitudAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudAnulacion.setCodigoMotivo(solicitud.getCodigoMotivo());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudAnulacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudAnulacion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudAnulacion.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudAnulacion.setCufd(solicitud.getCufd());
        solicitudAnulacion.setCuf(solicitud.getCuf());
        solicitudAnulacion.setCuis(solicitud.getCuis());
        solicitudAnulacion.setNit(solicitud.getNitEmpresa());
        solicitudAnulacion.setNumeroDocumentoFiscal(solicitud.getNumeroDocumentoFiscal());
        request.setSolicitudServicioAnulacion(solicitudAnulacion);
        JAXBElement<AnulacionFacturaElectronicaComercialExportacion> jaxbRequest = objectFactory.createAnulacionFacturaElectronicaComercialExportacion(request);
        JAXBElement<AnulacionFacturaElectronicaComercialExportacionResponse> anulacionFacturaElectronicaComercialExportacionResponse = (JAXBElement<AnulacionFacturaElectronicaComercialExportacionResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        AnulacionFacturaElectronicaComercialExportacionResponse response = anulacionFacturaElectronicaComercialExportacionResponse.getValue();

        Respuesta39124 respuesta = new Respuesta39124();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39124 validaFacturaElectronicaComercialExportacion(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionAnulacionFacturaElectronicaComercialExportacion request = objectFactory.createValidacionAnulacionFacturaElectronicaComercialExportacion();
        SolicitudValidacionAnulacion solicitudValidacionAnulacion = new SolicitudValidacionAnulacion();
        solicitudValidacionAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
        solicitudValidacionAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionAnulacion.setCodigoMotivo(solicitud.getCodigoMotivo());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudValidacionAnulacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionAnulacion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        solicitudValidacionAnulacion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionAnulacion.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudValidacionAnulacion.setCuf(solicitud.getCuf());
        solicitudValidacionAnulacion.setCufd(solicitud.getCufd());
        solicitudValidacionAnulacion.setCuis(solicitud.getCuis());
        solicitudValidacionAnulacion.setNit(solicitud.getNitEmpresa());
        solicitudValidacionAnulacion.setNumeroDocumentoFiscal(solicitud.getNumeroDocumentoFiscal());

        request.setSolicitudServicioValidacionAnulacion(solicitudValidacionAnulacion);
        JAXBElement<ValidacionAnulacionFacturaElectronicaComercialExportacion> jaxbRequest = objectFactory.createValidacionAnulacionFacturaElectronicaComercialExportacion(request);

        JAXBElement<ValidacionAnulacionFacturaElectronicaComercialExportacionResponse> validacionAnulacionFacturaElectronicaComercialExportacionResponse = (JAXBElement<ValidacionAnulacionFacturaElectronicaComercialExportacionResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);

        ValidacionAnulacionFacturaElectronicaComercialExportacionResponse response = validacionAnulacionFacturaElectronicaComercialExportacionResponse.getValue();

        Respuesta39124 respuesta = new Respuesta39124();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }
}
