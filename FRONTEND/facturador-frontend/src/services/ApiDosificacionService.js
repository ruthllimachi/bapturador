import Api from '@/services/Api'

export default {
    ///ApiDosificacion
    solicitudCuis(dato){
        return Api().post("/api/apiDosificacion/solicitudCuis", dato);
    },
    cierreOperacion(dato){
        return Api().post("/api/apiDosificacion/cierreOperacion", dato);
    },
    dosificacionVigente(login){
        return Api().get(`/api/apiDosificacion/getDosificacionVigente/${login}`);
    },
    getDosificacionVigenteBySucursal(idSucursal){
        return Api().get(`/api/apiDosificacion/getDosificacionVigenteBySucursal/${idSucursal}`);
    },
    getDosificacionVigenteByPuntoVenta(idPuntoVenta){
        return Api().get(`/api/apiDosificacion/getDosificacionVigenteByPuntoVenta/${idPuntoVenta}`);
    },
    listaDosificacionSucursalPorIdEmpresa(idEmpresa){
        return Api().get(`/api/apiDosificacion/listaDosificacionSucursalPorIdEmpresa/${idEmpresa}`);
    },
    listaDosificacionPuntoVentaPorIdEmpresa(idEmpresa){
        return Api().get(`/api/apiDosificacion/listaDosificacionPuntoVentaPorIdEmpresa/${idEmpresa}`);
    },
}
