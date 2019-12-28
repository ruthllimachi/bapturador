import Api from '@/services/Api'

export default { 
    //ApiNotaFiscalCreditoDebito
    leerPorLogin(login, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad){
        console.log("login", login);
        console.log("codigoDocumentoFiscal", codigoDocumentoFiscal);
        console.log("codigoDocumentoSector", codigoDocumentoSector);
        console.log("codigoTipoModalidad", codigoTipoModalidad);
        return Api().get(`/api/apiNotaFiscalCreditoDebito/leerPorLogin/${login}/${codigoDocumentoFiscal}/${codigoDocumentoSector}/${codigoTipoModalidad}`)
    },
}
