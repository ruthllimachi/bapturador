package com.bap.api.consumer;

import com.api.fesc_39112.AnulacionFacturaComputarizadaEstandar;
import com.api.fesc_39112.AnulacionFacturaComputarizadaEstandarResponse;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39112.ObjectFactory;
import com.api.fesc_39112.RecepcionFacturaComputarizadaEstandar;
import com.api.fesc_39112.RecepcionFacturaComputarizadaEstandarResponse;
import com.api.fesc_39112.SolicitudAnulacion;
import com.api.fesc_39112.SolicitudRecepcion;
import com.api.fesc_39112.SolicitudValidacionAnulacion;
import com.api.fesc_39112.SolicitudValidacionRecepcion;
import com.api.fesc_39112.ValidacionAnulacionFacturaComputarizadaEstandar;
import com.api.fesc_39112.ValidacionAnulacionFacturaComputarizadaEstandarResponse;
import com.api.fesc_39112.ValidacionRecepcionFacturaComputarizadaEstandar;
import com.api.fesc_39112.ValidacionRecepcionFacturaComputarizadaEstandarResponse;
import com.api.fesc_39112.VerificarComunicacion;
import com.api.fesc_39112.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39112;
import com.bap.api.dto.SolicitudCliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@SuppressWarnings("unchecked")
@Component("consumerWS39112")
public class ConsumerWS39112 {

    @Autowired
    @Qualifier("webServiceTemplate39112")
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

    public Respuesta39112 recepcionFacturaComputarizadaEstandar(SolicitudCliente solicitud) {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            RecepcionFacturaComputarizadaEstandar request = objectFactory.createRecepcionFacturaComputarizadaEstandar();

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

            JAXBElement<RecepcionFacturaComputarizadaEstandar> jaxbRequest = objectFactory.createRecepcionFacturaComputarizadaEstandar(request);
            JAXBElement<RecepcionFacturaComputarizadaEstandarResponse> recepcionFacturaComputarizadaEstandarResponse = (JAXBElement<RecepcionFacturaComputarizadaEstandarResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            RecepcionFacturaComputarizadaEstandarResponse response = recepcionFacturaComputarizadaEstandarResponse.getValue();

            Respuesta39112 respuesta = new Respuesta39112();
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

    public Respuesta39112 validacionRecepcionFacturaComputarizadaEstandar(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionRecepcionFacturaComputarizadaEstandar request = objectFactory.createValidacionRecepcionFacturaComputarizadaEstandar();

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

        JAXBElement<ValidacionRecepcionFacturaComputarizadaEstandar> jaxbRequest = objectFactory.createValidacionRecepcionFacturaComputarizadaEstandar(request);

        JAXBElement<ValidacionRecepcionFacturaComputarizadaEstandarResponse> validacionRecepcionFacturaComputarizadaEstandarResponse = (JAXBElement<ValidacionRecepcionFacturaComputarizadaEstandarResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ValidacionRecepcionFacturaComputarizadaEstandarResponse response = validacionRecepcionFacturaComputarizadaEstandarResponse.getValue();

        Respuesta39112 respuesta = new Respuesta39112();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39112 anulaFacturaComputarizadaEstandar(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        AnulacionFacturaComputarizadaEstandar request = objectFactory.createAnulacionFacturaComputarizadaEstandar();
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
        JAXBElement<AnulacionFacturaComputarizadaEstandar> jaxbRequest = objectFactory.createAnulacionFacturaComputarizadaEstandar(request);
        JAXBElement<AnulacionFacturaComputarizadaEstandarResponse> anulacionFacturaComputarizadaEstandarResponse = (JAXBElement<AnulacionFacturaComputarizadaEstandarResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        AnulacionFacturaComputarizadaEstandarResponse response = anulacionFacturaComputarizadaEstandarResponse.getValue();

        Respuesta39112 respuesta = new Respuesta39112();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39112 validaAnulacionFacturaComputarizadaEstandar(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionAnulacionFacturaComputarizadaEstandar request = objectFactory.createValidacionAnulacionFacturaComputarizadaEstandar();
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
        JAXBElement<ValidacionAnulacionFacturaComputarizadaEstandar> jaxbRequest = objectFactory.createValidacionAnulacionFacturaComputarizadaEstandar(request);

        JAXBElement<ValidacionAnulacionFacturaComputarizadaEstandarResponse> validacionAnulacionFacturaComputarizadaEstandarResponse = (JAXBElement<ValidacionAnulacionFacturaComputarizadaEstandarResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);

        ValidacionAnulacionFacturaComputarizadaEstandarResponse response = validacionAnulacionFacturaComputarizadaEstandarResponse.getValue();

        Respuesta39112 respuesta = new Respuesta39112();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

}
