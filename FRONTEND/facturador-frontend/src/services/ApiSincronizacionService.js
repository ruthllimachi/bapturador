import Api from '@/services/Api'

export default {
    ///ApiSincronizacion
    getDatosSincronizados(grupo){
        return Api().get("/api/apiSincronizacion/codigoCatalogos", { params: { grupo: grupo }})
    },
    sincronizacionPorSolicitud(grupo,login, codigoAutorizacion){
        return Api().get(`/api/apiSincronizacion/sincronizacionBySolicitud/${grupo}/${login}/${codigoAutorizacion}`)
    },
    sincronizacionDiaria(login){
        return Api().get(`/api/apiSincronizacion/sincronizacionDiaria/${login}`)
    },
}
