import Api from '@/services/Api'

export default { 
    //ApiNotaFiscalCreditoDebito
    leerPorLogin(login, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad){
        return Api().get(`/api/apiNotaFiscalCreditoDebito/leerPorLogin/${login}/${codigoDocumentoFiscal}/${codigoDocumentoSector}/${codigoTipoModalidad}`)
    },
}
