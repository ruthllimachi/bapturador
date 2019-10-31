import ApiDosificacion from './ApiDosificacion';
import ApiConfiguracion from './ApiConfiguracion';
import ApiCliente from './ApiCliente';
import ParTipoMoneda from './ParTipoMoneda';
import ParTipoMetodoPago from './ParTipoMetodoPago';
import ParTipoEmision from './ParTipoEmision';
import ParLeyendaFactura from './ParLeyendaFactura';
import ApiFacturaDetalle from './ApiFacturaDetalle';
import ParCondicion from './ParCondicion';

let ApiFactura = {
    idFactura:null,
    nitEmisor:null,
    numeroFactura:null,
    apiDosificacion:ApiDosificacion,
    apiConfiguracion:ApiConfiguracion,
    apiCliente:ApiCliente,
    parTipoMetodoPago:ParTipoMetodoPago,
    parTipoMoneda:ParTipoMoneda,
    parTipoEmision:ParTipoEmision,
    parLeyendaFactura:ParLeyendaFactura,
    apiFacturaDetalle:ApiFacturaDetalle,
    parCondicion:ParCondicion
}
export default ApiFactura;