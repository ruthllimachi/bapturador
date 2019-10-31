import Api from '@/services/Api'

export default { 
    //ApiFacturas
    leerPorLogin(login, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad){
        return Api().get(`/api/apiFactura/leerPorLogin/${login}/${codigoDocumentoFiscal}/${codigoDocumentoSector}/${codigoTipoModalidad}`)
    },
    facturaXcliente(id){
    return Api().get(`/api/apiFactura/getFacturaByCliente/${id}`)
    },

    generarReporte(idFactura){       
        return Api().get(`/api/apiFactura/generarReporte/${idFactura}`,{            
            responseType: 'blob',
        })       
    },

}
