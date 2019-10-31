package com.bap.api.consumer;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.bap.api.dto.Respuesta39118;
import com.bap.api.dto.SolicitudCliente;
import com.api.fesc_39118.ObjectFactory;
import com.api.fesc_39118.RecepcionSolicitudNuevoValorProducto;
import com.api.fesc_39118.RecepcionSolicitudNuevoValorProductoResponse;
import com.api.fesc_39118.SincronizarActividades;
import com.api.fesc_39118.SincronizarActividadesResponse;
import com.api.fesc_39118.SincronizarListaLeyendasFactura;
import com.api.fesc_39118.SincronizarListaLeyendasFacturaResponse;
import com.api.fesc_39118.SincronizarListaMensajesServicios;
import com.api.fesc_39118.SincronizarListaMensajesServiciosResponse;
import com.api.fesc_39118.SincronizarListaProductosServicios;
import com.api.fesc_39118.SincronizarListaProductosServiciosResponse;
import com.api.fesc_39118.SincronizarParametricaEventosSignificativos;
import com.api.fesc_39118.SincronizarParametricaEventosSignificativosResponse;
import com.api.fesc_39118.SincronizarParametricaMotivoAnulacion;
import com.api.fesc_39118.SincronizarParametricaMotivoAnulacionResponse;
import com.api.fesc_39118.SincronizarParametricaPaisOrigen;
import com.api.fesc_39118.SincronizarParametricaPaisOrigenResponse;
import com.api.fesc_39118.SincronizarParametricaTipoAmbiente;
import com.api.fesc_39118.SincronizarParametricaTipoAmbienteResponse;
import com.api.fesc_39118.SincronizarParametricaTipoComponente;
import com.api.fesc_39118.SincronizarParametricaTipoComponenteResponse;
import com.api.fesc_39118.SincronizarParametricaTipoDepartamento;
import com.api.fesc_39118.SincronizarParametricaTipoDepartamentoResponse;
import com.api.fesc_39118.SincronizarParametricaTipoDocumentoFiscal;
import com.api.fesc_39118.SincronizarParametricaTipoDocumentoFiscalResponse;
import com.api.fesc_39118.SincronizarParametricaTipoDocumentoIdentidad;
import com.api.fesc_39118.SincronizarParametricaTipoDocumentoIdentidadResponse;
import com.api.fesc_39118.SincronizarParametricaTipoDocumentoSector;
import com.api.fesc_39118.SincronizarParametricaTipoDocumentoSectorResponse;
import com.api.fesc_39118.SincronizarParametricaTipoEmision;
import com.api.fesc_39118.SincronizarParametricaTipoEmisionResponse;
import com.api.fesc_39118.SincronizarParametricaTipoMetodoPago;
import com.api.fesc_39118.SincronizarParametricaTipoMetodoPagoResponse;
import com.api.fesc_39118.SincronizarParametricaTipoModalidad;
import com.api.fesc_39118.SincronizarParametricaTipoModalidadResponse;
import com.api.fesc_39118.SincronizarParametricaTipoMoneda;
import com.api.fesc_39118.SincronizarParametricaTipoMonedaResponse;
import com.api.fesc_39118.SincronizarParametricaTipoPuntoVenta;
import com.api.fesc_39118.SincronizarParametricaTipoPuntoVentaResponse;
import com.api.fesc_39118.SincronizarParametricaUnidadMedida;
import com.api.fesc_39118.SincronizarParametricaUnidadMedidaResponse;
import com.api.fesc_39118.SolicitudSincronizacion;
import com.api.fesc_39118.SolicitudValidacionValor;
import com.api.fesc_39118.SolicitudValorNuevo;
import com.api.fesc_39118.ValidacionSolicitudNuevoValorProducto;
import com.api.fesc_39118.ValidacionSolicitudNuevoValorProductoResponse;
import com.api.fesc_39118.VerificarComunicacion;
import com.api.fesc_39118.VerificarComunicacionResponse;

//Falta estos parametros  a todos 
//<codigoAutorizacion>[int?]</codigoAutorizacion>
//<codigoPuntoVenta>[int?]</codigoPuntoVenta>
//codigoPuntoVenta
//Solo se env??a cuando el punto de venta va a sincronizar (1, 2,..,n)
//codigoAutorizacion 
//Solo se env??a cuando se tiene el c??digo de  sincronizaci??n excepcional
//Existen dos tipos de procesos:
//a) El primero que permite la sincronizaci??n diaria a trav??s del consumo del servicio
//b) El segundo de sincronizaci??n por solicitud que permite realizar una nueva sincronizaci??n en el d??a, previa obtenci??n de un
//c??digo de autorizaci??n v??a soporte.
@Component("consumerWS39118")
public class ConsumerWS39118 {

    @Autowired
    @Qualifier("webServiceTemplate39118")
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
            System.out.println("#####Error "+  e.getMessage());
            return -1;
        }
    }

    public Respuesta39118 sincronizarParametricaEventoSignificativo(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }
        SincronizarParametricaEventosSignificativos request = objectFactory
                .createSincronizarParametricaEventosSignificativos();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaEventosSignificativos> jaxbRequest = objectFactory
                .createSincronizarParametricaEventosSignificativos(request);
        JAXBElement<SincronizarParametricaEventosSignificativosResponse> sincronizarParametricaEventosSignificativosResponse = (JAXBElement<SincronizarParametricaEventosSignificativosResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaEventosSignificativosResponse response = sincronizarParametricaEventosSignificativosResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaMotivoAnulacion(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }
        SincronizarParametricaMotivoAnulacion request = objectFactory.createSincronizarParametricaMotivoAnulacion();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaMotivoAnulacion> jaxbRequest = objectFactory
                .createSincronizarParametricaMotivoAnulacion(request);
        JAXBElement<SincronizarParametricaMotivoAnulacionResponse> sincronizarParametricaMotivoAnulacionResponse = (JAXBElement<SincronizarParametricaMotivoAnulacionResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaMotivoAnulacionResponse response = sincronizarParametricaMotivoAnulacionResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoAmbiente(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }
        SincronizarParametricaTipoAmbiente request = objectFactory.createSincronizarParametricaTipoAmbiente();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarParametricaTipoAmbiente> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoAmbiente(request);
        JAXBElement<SincronizarParametricaTipoAmbienteResponse> sincronizarParametricaTipoAmbienteResponse = (JAXBElement<SincronizarParametricaTipoAmbienteResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoAmbienteResponse response = sincronizarParametricaTipoAmbienteResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarLeyendaFactura(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarListaLeyendasFactura request = objectFactory.createSincronizarListaLeyendasFactura();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarListaLeyendasFactura> jaxbRequest = objectFactory
                .createSincronizarListaLeyendasFactura(request);
        JAXBElement<SincronizarListaLeyendasFacturaResponse> sincronizarListaLeyendasFacturaResponse = (JAXBElement<SincronizarListaLeyendasFacturaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarListaLeyendasFacturaResponse response = sincronizarListaLeyendasFacturaResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoDocumentoIdentidad(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoDocumentoIdentidad request = objectFactory
                .createSincronizarParametricaTipoDocumentoIdentidad();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoDocumentoIdentidad> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoDocumentoIdentidad(request);

        JAXBElement<SincronizarParametricaTipoDocumentoIdentidadResponse> sincronizarParametricaTipoDocumentoIdentidadResponse = (JAXBElement<SincronizarParametricaTipoDocumentoIdentidadResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoDocumentoIdentidadResponse response = sincronizarParametricaTipoDocumentoIdentidadResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoDocumentoSector(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }
        SincronizarParametricaTipoDocumentoSector request = objectFactory
                .createSincronizarParametricaTipoDocumentoSector();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarParametricaTipoDocumentoSector> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoDocumentoSector(request);
        JAXBElement<SincronizarParametricaTipoDocumentoSectorResponse> sincronizarParametricaTipoDocumentoSectorResponse = (JAXBElement<SincronizarParametricaTipoDocumentoSectorResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoDocumentoSectorResponse response = sincronizarParametricaTipoDocumentoSectorResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoDepartamento(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }
        SincronizarParametricaTipoDepartamento request = objectFactory.createSincronizarParametricaTipoDepartamento();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarParametricaTipoDepartamento> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoDepartamento(request);
        JAXBElement<SincronizarParametricaTipoDepartamentoResponse> sincronizarParametricaTipoDepartamentoResponse = (JAXBElement<SincronizarParametricaTipoDepartamentoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoDepartamentoResponse response = sincronizarParametricaTipoDepartamentoResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoModalidad(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoModalidad request = objectFactory.createSincronizarParametricaTipoModalidad();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarParametricaTipoModalidad> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoModalidad(request);
        JAXBElement<SincronizarParametricaTipoModalidadResponse> sincronizarParametricaTipoModalidadResponse = (JAXBElement<SincronizarParametricaTipoModalidadResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoModalidadResponse response = sincronizarParametricaTipoModalidadResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoDocumentoFiscal(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }
        SincronizarParametricaTipoDocumentoFiscal request = objectFactory
                .createSincronizarParametricaTipoDocumentoFiscal();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoDocumentoFiscal> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoDocumentoFiscal(request);
        JAXBElement<SincronizarParametricaTipoDocumentoFiscalResponse> sincronizarParametricaTipoDocumentoFiscalResponse = (JAXBElement<SincronizarParametricaTipoDocumentoFiscalResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoDocumentoFiscalResponse response = sincronizarParametricaTipoDocumentoFiscalResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarMensajeServicio(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarListaMensajesServicios request = objectFactory.createSincronizarListaMensajesServicios();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarListaMensajesServicios> jaxbRequest = objectFactory
                .createSincronizarListaMensajesServicios(request);
        JAXBElement<SincronizarListaMensajesServiciosResponse> sincronizarListaMensajesServiciosResponse = (JAXBElement<SincronizarListaMensajesServiciosResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarListaMensajesServiciosResponse response = sincronizarListaMensajesServiciosResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoMetodoPago(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoMetodoPago request = objectFactory.createSincronizarParametricaTipoMetodoPago();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoMetodoPago> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoMetodoPago(request);
        JAXBElement<SincronizarParametricaTipoMetodoPagoResponse> sincronizarParametricaTipoMetodoPagoResponse = (JAXBElement<SincronizarParametricaTipoMetodoPagoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoMetodoPagoResponse response = sincronizarParametricaTipoMetodoPagoResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoComponente(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoComponente request = objectFactory.createSincronizarParametricaTipoComponente();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoComponente> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoComponente(request);
        JAXBElement<SincronizarParametricaTipoComponenteResponse> sincronizarParametricaTipoComponenteResponse = (JAXBElement<SincronizarParametricaTipoComponenteResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoComponenteResponse response = sincronizarParametricaTipoComponenteResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoEmision(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoEmision request = objectFactory.createSincronizarParametricaTipoEmision();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoEmision> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoEmision(request);
        JAXBElement<SincronizarParametricaTipoEmisionResponse> sincronizarParametricaTipoEmisionResponse = (JAXBElement<SincronizarParametricaTipoEmisionResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoEmisionResponse response = sincronizarParametricaTipoEmisionResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaPaisOrigen(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaPaisOrigen request = objectFactory.createSincronizarParametricaPaisOrigen();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaPaisOrigen> jaxbRequest = objectFactory
                .createSincronizarParametricaPaisOrigen(request);
        JAXBElement<SincronizarParametricaPaisOrigenResponse> sincronizarParametricaPaisOrigenResponse = (JAXBElement<SincronizarParametricaPaisOrigenResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaPaisOrigenResponse response = sincronizarParametricaPaisOrigenResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarParametricaTipoMoneda(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoMoneda request = objectFactory.createSincronizarParametricaTipoMoneda();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoMoneda> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoMoneda(request);
        JAXBElement<SincronizarParametricaTipoMonedaResponse> sincronizarParametricaTipoMonedaResponse = (JAXBElement<SincronizarParametricaTipoMonedaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoMonedaResponse response = sincronizarParametricaTipoMonedaResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    /**
     *
     * La respuesta esta transaccion y listaParametricasDto
     *
     * @param solicitudCliente
     * @return
     */
    public Respuesta39118 sincronizarParametricaUnidadMedida(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaUnidadMedida request = objectFactory.createSincronizarParametricaUnidadMedida();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaUnidadMedida> jaxbRequest = objectFactory
                .createSincronizarParametricaUnidadMedida(request);

        JAXBElement<SincronizarParametricaUnidadMedidaResponse> sincronizarParametricaUnidadMedidaResponse = (JAXBElement<SincronizarParametricaUnidadMedidaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaUnidadMedidaResponse response = sincronizarParametricaUnidadMedidaResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    /**
     *
     * La respuesta esta transaccion y listaParametricasDto
     *
     * @param solicitudCliente
     * @return
     */
    public Respuesta39118 sincronizarParametricaTipoPuntoVenta(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());        
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarParametricaTipoPuntoVenta request = objectFactory.createSincronizarParametricaTipoPuntoVenta();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarParametricaTipoPuntoVenta> jaxbRequest = objectFactory
                .createSincronizarParametricaTipoPuntoVenta(request);

        JAXBElement<SincronizarParametricaTipoPuntoVentaResponse> sincronizarParametricaTipoPuntoVentaResponse = (JAXBElement<SincronizarParametricaTipoPuntoVentaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarParametricaTipoPuntoVentaResponse response = sincronizarParametricaTipoPuntoVentaResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaParametricas().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaParametricas().getListaCodigosRespuestas());
        respuesta.setListaParametricasDto(response.getRespuestaListaParametricas().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 sincronizarActividades(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarActividades request = objectFactory.createSincronizarActividades();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarActividades> jaxbRequest = objectFactory.createSincronizarActividades(request);

        JAXBElement<SincronizarActividadesResponse> sincronizarActividadesResponse = (JAXBElement<SincronizarActividadesResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarActividadesResponse response = sincronizarActividadesResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaActividades().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaActividades().getListaCodigosRespuestas());
        respuesta.setListaActividadesDto(response.getRespuestaListaActividades().getListaActividades());
        return respuesta;
    }

    public Respuesta39118 sincronizarListaProductoServicio(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        if (solicitudCliente.getCodigoAutorizacion() > 0) {
            JAXBElement<Integer> codigoAutorizacion = objectFactory.createSolicitudSincronizacionCodigoAutorizacion(solicitudCliente.getCodigoAutorizacion());
            solicitudSincronizacion.setCodigoAutorizacion(codigoAutorizacion);
        }

        SincronizarListaProductosServicios request = objectFactory.createSincronizarListaProductosServicios();
        request.setSolicitudSincronizacion(solicitudSincronizacion);

        JAXBElement<SincronizarListaProductosServicios> jaxbRequest = objectFactory
                .createSincronizarListaProductosServicios(request);
        JAXBElement<SincronizarListaProductosServiciosResponse> sincronizarListaProductosServiciosResponse = (JAXBElement<SincronizarListaProductosServiciosResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarListaProductosServiciosResponse response = sincronizarListaProductosServiciosResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaListaProductos().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaListaProductos().getListaCodigosRespuestas());
        respuesta.setListaProductosDto(response.getRespuestaListaProductos().getListaCodigos());
        return respuesta;
    }

    public Respuesta39118 recepcionSolicitudNuevoValorProducto(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudValorNuevo solicitudValorNuevo = objectFactory.createSolicitudValorNuevo();
        solicitudValorNuevo.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudValorNuevo.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudValorNuevo.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        solicitudValorNuevo.setCuis(solicitudCliente.getCuis());
        solicitudValorNuevo.setNit(solicitudCliente.getNitEmpresa());

        RecepcionSolicitudNuevoValorProducto request = objectFactory.createRecepcionSolicitudNuevoValorProducto();
        request.setSolicitudValorNuevo(solicitudValorNuevo);

        JAXBElement<RecepcionSolicitudNuevoValorProducto> jaxbRequest = objectFactory
                .createRecepcionSolicitudNuevoValorProducto(request);
        JAXBElement<RecepcionSolicitudNuevoValorProductoResponse> recepcionSolicitudNuevoValorProductoResponse = (JAXBElement<RecepcionSolicitudNuevoValorProductoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        RecepcionSolicitudNuevoValorProductoResponse response = recepcionSolicitudNuevoValorProductoResponse.getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaSolicitudNuevoValor().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaSolicitudNuevoValor().getListaCodigosRespuestas());
        respuesta.setCodigoSolicitud(response.getRespuestaSolicitudNuevoValor().getCodigoSolicitud());
        return respuesta;
    }

    public Respuesta39118 validacionSolicitudNuevoValorProducto(
            SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();

        SolicitudValidacionValor solicitudValidacionValor = objectFactory.createSolicitudValidacionValor();
        solicitudValidacionValor.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudValidacionValor.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudValidacionValor.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        solicitudValidacionValor.setCuis(solicitudCliente.getCuis());
        solicitudValidacionValor.setNit(solicitudCliente.getNitEmpresa());
        solicitudValidacionValor.setCodigoSolicitud(solicitudCliente.getCodigoSolicitud());

        ValidacionSolicitudNuevoValorProducto request = objectFactory.createValidacionSolicitudNuevoValorProducto();
        request.setSolicitudValidacionValor(solicitudValidacionValor);

        JAXBElement<ValidacionSolicitudNuevoValorProducto> jaxbRequest = objectFactory
                .createValidacionSolicitudNuevoValorProducto(request);
        JAXBElement<ValidacionSolicitudNuevoValorProductoResponse> validacionSolicitudNuevoValorProductoResponse = (JAXBElement<ValidacionSolicitudNuevoValorProductoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ValidacionSolicitudNuevoValorProductoResponse response = validacionSolicitudNuevoValorProductoResponse
                .getValue();

        Respuesta39118 respuesta = new Respuesta39118();
        respuesta.setTransaccion(response.getRespuestaNuevoValor().isTransaccion());
        respuesta.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaNuevoValor().getListaCodigosRespuestas());
        respuesta.setCodigoActividad(response.getRespuestaNuevoValor().getCodigoActividad());
        respuesta.setCodigoProducto(response.getRespuestaNuevoValor().getCodigoProducto());
        respuesta.setDescripcionProducto(response.getRespuestaNuevoValor().getDescripcionProducto());
        return respuesta;

    }
}
