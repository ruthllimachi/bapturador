import ApiPuntoVenta from './ApiPuntoVenta';
import ApiSucursal from './ApiSucursal';

let ApiConfiguracion = {
    "idConfiguracion":null,
    "idEmpresa":null,
    "apiSucursal":ApiSucursal,
    "apiPuntoVenta":ApiPuntoVenta,
    "cufd":null,
    "fechaHora":null,
    "fechaVigencia":null,
    "descripcionSucursal":'',
    "descripcionPuntoVenta":''
}
export default ApiConfiguracion;

