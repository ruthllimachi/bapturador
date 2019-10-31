import Api from '@/services/Api'

export default {
    ///ApiConfiguracion
    getApiConfiguracionPorIdEmpresa(idEmpresa){
        return Api().get(`/api/apiConfiguracion/getPorIdEmpresa/${idEmpresa}`);
    },
    postApiConfiguracion(dato){
        return Api().post("/api/apiConfiguracion", dato);
    },
    getConfiguracionVigenteBySucursal(idSucursal){
        return Api().get(`/api/apiConfiguracion/getConfiguracionVigenteBySucursal/${idSucursal}`);
    },
    getConfiguracionVigenteByPuntoVenta(idPuntoVenta){
        return Api().get(`/api/apiConfiguracion/getConfiguracionVigenteByPuntoVenta/${idPuntoVenta}`);
    },
    solicitudCufd(login){
        return Api().get(`/api/apiConfiguracion/solicitudCufd/${login}`);
    },
    sincronizarFechaHora(login){
        return Api().get(`/api/apiConfiguracion/sincronizarFechaHora/${login}`);
    },
}    