import Api from '@/services/Api'

export default { 
    //ApiFacturaComercialExportacion
    leerPorLogin(login, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad){
        return Api().get(`/api/apiFacturaComercialExportacion/leerPorLogin/${login}/${codigoDocumentoFiscal}/${codigoDocumentoSector}/${codigoTipoModalidad}`)
    },
}
