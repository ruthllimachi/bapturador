import ApiDosificacion from './ApiDosificacion';
import ApiConfiguracion from './ApiConfiguracion';
import ParTipoEmision from './ParTipoEmision';
import ParLeyendaFactura from './ParLeyendaFactura';
import ApiNotaFiscalCreditoDebitoDetalle from './ApiNotaFiscalCreditoDebitoDetalle';
import ApiFactura from './ApiFactura';
import ParCondicion from './ParCondicion';

let ApiNotaFiscalCreditoDebito = {
    idNotaCreditoDebito:null,    
    numeroNotaCreditoDebito:null,
    apiDosificacion:ApiDosificacion,
    apiConfiguracion:ApiConfiguracion,    
    parTipoEmision:ParTipoEmision,
    parLeyendaFactura:ParLeyendaFactura,
    apiFactura:ApiFactura,
    apiNotaFiscalCreditoDebitoDetalle:ApiNotaFiscalCreditoDebitoDetalle,
    parCondicion:ParCondicion
}
export default ApiNotaFiscalCreditoDebito;