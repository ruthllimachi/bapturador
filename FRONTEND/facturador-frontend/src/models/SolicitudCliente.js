import ParTipoModalidad from './ParTipoModalidad';
import ParTipoPuntoVenta from './ParTipoPuntoVenta';
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
    "usuario":null
}
export default SolicitudCliente;