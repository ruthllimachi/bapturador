import ParTipoModalidad from './ParTipoModalidad';
import ParTipoPuntoVenta from './ParTipoPuntoVenta';
import ParEventoSignificativo from './ParEventoSignificativo';
import ApiPuntoVenta from './ApiPuntoVenta';
import ApiSucursal from './ApiSucursal';

let SolicitudCliente = {
    "codigoAmbiente":null,
    "codigoAutorizacion":null,
    "codigoPuntoVenta":null,
    "codigoSistema":null,
    "codigoSucursal":null,
    "cuis":null,
    "nit":null,    
    "codigoModalidad":null,
    "codigoTipoPuntoVenta":null,   
    "apiSucursal":ApiSucursal,
    "apiPuntoVenta":ApiPuntoVenta,
    "parTipoPuntoVenta":ParTipoPuntoVenta,
    "parTipoModalidad":ParTipoModalidad,
    "login":null,
    "password":null,
    "descripcion":null,
    "parEventoSignificativo":ParEventoSignificativo,
    "codigoEvento":null,
}
export default SolicitudCliente;