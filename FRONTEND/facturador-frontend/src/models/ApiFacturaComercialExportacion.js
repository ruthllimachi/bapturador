import ApiDosificacion from './ApiDosificacion';
import ApiConfiguracion from './ApiConfiguracion';
import ApiCliente from './ApiCliente';
import ParTipoMoneda from './ParTipoMoneda';
import ParTipoMetodoPago from './ParTipoMetodoPago';
import ParTipoEmision from './ParTipoEmision';
import ParLeyendaFactura from './ParLeyendaFactura';
import ApiFacturaComercialExportacionDetalle from './ApiFacturaComercialExportacionDetalle';
import ParCondicion from './ParCondicion';

let ApiFacturaComercialExportacion = {
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
    parCondicion:ParCondicion,
    apiFacturaComercialExportacionDetalle:ApiFacturaComercialExportacionDetalle
}
export default ApiFacturaComercialExportacion;